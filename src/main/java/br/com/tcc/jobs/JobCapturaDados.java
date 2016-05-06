package br.com.tcc.jobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.tcc.service.DadosClimaService;
import br.com.tcc.service.Query;

/**
 * Classe obsoleta, seu uso requeria configuração para ser iniciada quando a
 * aplicação estivesse no tomcat, necessita de um listener em web.xml,
 * atualmente faz-se uso de arquivos configurados como schedules pelo Spring
 *
 * @author Rodrigo
 *
 */
@Deprecated
public class JobCapturaDados implements ServletContextListener {

    /**
     *
     * URL : endereço de onde os dados captados serão consumidos
     *
     * DELAY: intervalo entre cada chamada a URL
     *
     * OBS: ao alterar o valor do delay, também alterar o valor http-refresh nas
     * páginas estacao e listagem
     */

    // DESCOMENTAR PARA AMBIENTE DE PRODUÇÃO COMENTANDO linha 45
    // private static final String URL = "http://172.16.7.162/";
    private static final String  URL   = "http://localhost:8080/teste/protocolo";
    private static final Integer DELAY = 60;

    @Inject
    private DadosClimaService    dadosClimaService;

    @Inject
    private Query                query;

    // private static final Integer hora = 23;
    // private static final Integer minuto = 24;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {}

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        /*
         * injeta o contexto da aplicação para possibilitar a injeção de
         * dependências do spring
         */

        /*
         * http://stackoverflow.com/questions/5511152/dependency-inject-servlet-
         * listener
         */
        WebApplicationContextUtils
            .getRequiredWebApplicationContext(servletContextEvent.getServletContext())
            .getAutowireCapableBeanFactory()
            .autowireBean(this);

        System.out.println("\n >>>>>>>>>>>> \n JOB DE CAPTURA INICIADO \n >>>>>>>>>>>>\n");

        TimerTask vodTimer = new VodTimerTask();
        Timer timer = new Timer();
        timer.schedule(vodTimer, 1000, (DELAY * 1000));

    }

    class VodTimerTask extends TimerTask {

        @Override
        public void run() {
            try {

                String content = doHttpConnection();
                if (content != null) {

                    if (content.startsWith("#") &&
                        content.endsWith("#")) {

                        dadosClimaService.insereDadosCaptados(content);
                        System.out.println("\n >>>>>>>>>>>> \n DADO INSERIDO > LENDO A PARTIR " + URL +
                            " as " + new Date() + "\n >>>>>>>>>>>>\n");

                        /* insere nas tabelas auxiliares somenta a 00:01 */
                        Calendar cal = Calendar.getInstance();
                        // if (cal.get(Calendar.HOUR_OF_DAY) == hora
                        // && cal.get(Calendar.MINUTE) == minuto) {

                        if (cal.get(Calendar.MINUTE) % 2 == 0) {
                            selectAndInsert();
                        }

                    } else {
                        System.out.println("\n >>>>>>>>>>>> \n DADOS FORA DO PADRAO " + new Date() + " \n >>>>>>>>>>>>\n");
                    }

                }

            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("\n >>>>>>>>>>>> \n EXCEPTION: " + e.getMessage() + "\n >>>>>>>>>>>>\n");

            }
        }
    }

    public void selectAndInsert() throws InterruptedException {

        // delay 5s
        Thread.sleep(5000);
        /* insere na tabela medicoes_dia e medicoes_hora */

        String select = "select round(min(t.valor),2) as temperaturaminima, round(avg(t.valor),2) as temperaturamedia , round(max(t.valor),2) as temperaturamaxima,"
            +
            "min(estacao_id) as estacao_id, min(t.data::timestamp) as data from temperatura t " +
            "where extract('day' from t.data) = 30 group by extract('hour' from t.data) order by data asc";
        query.getTest(select);

        for (int i = 0; i < 5; i++) {
            query.execute("insert into test values ('1','1')");
            query.execute("insert into test values ('2','2')");
        }

    }

    public String doHttpConnection() throws Exception {

        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try
        {
            url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // give it 5 seconds to respond
            connection.setReadTimeout(5 * 1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString().replaceAll("\\<.*?>", "").trim();

        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }

    }

}
