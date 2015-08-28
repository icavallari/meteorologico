package br.com.tcc.jobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.tcc.service.DadosClimaService;

/**
 * classe responsavel por conectar ao host onde as informações são coletadas
 * inserindo cada dado em sua tabela para posterior uso.
 *
 * @author Rodrigo
 *
 */

@Configuration
@EnableAsync
@EnableScheduling
@Named
public class JobTabelasIndividuais {

//    private static final String URL = "http://172.16.7.162/";
     private static final String URL = "http://localhost/teste/protocolo";

    @Inject
    private DadosClimaService   dadosClimaService;

    /**
     * executa a todo minuto quando segundos for 0
     */
    @Scheduled(cron = "0 */1 * * * *")
    public void buscarDadosArduino() {

        try {

            String content = doHttpConnection();
            if (content != null) {

                if (content.startsWith("#") &&
                    content.endsWith("#")) {

                    dadosClimaService.insereDadosCaptados(content);
                    System.out.println("\n >>>>>>>>>>>> \n DADO INSERIDO > LENDO A PARTIR " + URL +
                        " as " + new Date() + "\n >>>>>>>>>>>>\n");

                } else {
                    System.out.println("\n >>>>>>>>>>>> \n DADOS FORA DO PADRAO " + new Date() + " \n >>>>>>>>>>>>\n");
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("\n >>>>>>>>>>>> \n EXCEPTION: " + e.getMessage() + "\n >>>>>>>>>>>>\n");

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

            connection.setRequestMethod("GET");

            connection.setReadTimeout(5 * 1000);
            connection.connect();

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
