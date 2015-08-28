package br.com.tcc.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;

import org.apache.log4j.Level;
import org.junit.Ignore;

import br.com.tcc.service.Query;

/**
 * classe responsavel por realizar inserts ao banco de dados dentre um intervalo
 * determinado, porém não é viavel devido a demora para realizar inserts para
 * todo minuto em um intervalo de um ano, de tal modo que um script sql
 * diretamente pelo prompt de comando no Postgres é mais eficiente
 *
 * @author Rodrigo
 *
 */
@Ignore
public class InsertsJdbcTest extends SpringTest {

    Random                       random     = new Random();

    List<String>                 chuvas     = Arrays.asList(
                                                "Intesidade Baixa.",
                                                "Intensidade Moderada.",
                                                "Intensidade Alta.");

    List<String>                 ventos     = Arrays.asList(
                                                "NORTE.",
                                                "NOR-NORDESTE.",
                                                "NORDESTE.",
                                                "ES-NORDESTE.",
                                                "LESTE.",
                                                "ES-SUDESTE.",
                                                "SUDESTE.",
                                                "SU-SUDESTE.",
                                                "SUL.",
                                                "SU-SUDOESTE.",
                                                "SUDOESTE.",
                                                "OES-SUDOESTE.",
                                                "OESTE.",
                                                "OES-NOROESTE.",
                                                "NOROESTE.",
                                                "NOR-NOROESTE.");

    private static final String  delete     = "truncate estacao, altitude, chuva, estacao, pressaoatmosferica, rotacao, sentidovento, temperatura, umidade, velocidade;";
    private static final String  estacao    = "INSERT INTO estacao (id, cidade, data_criacao) values (nextval('seq_estacao'), 'salto', now());";
    private static final String  seqEstacao = "alter sequence seq_estacao restart with 1;";

    private static final Integer IDESTACAO  = 1;

    @Inject
    private Query                query;

    static {
        Log4jManager.setLevel(Level.INFO);
    }

    public void t() throws ParseException {

        query.execute(delete);
        query.execute(seqEstacao);
        query.execute(estacao);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String dataIni = "01/01/2015";
        String dataFim = "30/12/2015";

        Calendar c = new GregorianCalendar();
        c.setTime(sdf.parse(dataIni));

        do {

            c.add(Calendar.MINUTE, 1);

            insert(getDados(), c.getTime());
            // System.out.println(c.getTime());

        } while (c.getTime().before(sdf.parse(dataFim)));

    }

    public String getDados() {
        return "p" + random.nextInt(90000) + ",a455.61,r"
            + String.format(Locale.ENGLISH, "%.2f", random.nextFloat() * 90.90) + ",v"
            + String.format(Locale.ENGLISH, "%.2f", random.nextFloat() * 90.90) + ",s"
            + ventos.get(random.nextInt(16)) + ",u"
            + String.format(Locale.ENGLISH, "%.2f", random.nextFloat() * 90.90) + ",t"
            + String.format(Locale.ENGLISH, "%.2f", random.nextFloat() * 90.90) + ",c"
            + chuvas.get(random.nextInt(3));
    }

    public void insert(String html, Date data) {

        String[] dadosSeparados = html.split(",");

        for (int i = 0; i < dadosSeparados.length; i++) {

            if (dadosSeparados[i].startsWith("p")) {
                insertPressao(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("a")) {
                insertAltitude(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("s")) {
                insertVento(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("v")) {
                insertVelocidade(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("r")) {
                insertRotacao(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("c")) {
                insertChuva(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("u")) {
                insertUmidade(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }

            if (dadosSeparados[i].startsWith("t")) {
                insertTemperatura(dadosSeparados[i].substring(1, dadosSeparados[i].length()), data);
            }
        }

    }

    public void insertPressao(String valor, Date data) {
        String sql = "insert into pressaoatmosferica (id, estacao_id, valor, data) values(nextval('seq_pressaoatmosferica')," +
            IDESTACAO + "," + valor + ", '" + data + "' );";
        query.execute(sql);
    }

    public void insertAltitude(String valor, Date data) {
        String sql = "insert into altitude (id, estacao_id, valor, data) values(nextval('seq_altitude')," +
            IDESTACAO + "," + valor + ", '" + data + "' );";
        query.execute(sql);
    }

    public void insertVento(String valor, Date data) {
        String sql = "insert into sentidovento (id, estacao_id, valor, data) values(nextval('seq_sentidovento')," +
            IDESTACAO + ",'" + valor + "', '" + data + "' );";
        query.execute(sql);
    }

    public void insertVelocidade(String valor, Date data) {
        String sql = "insert into velocidade (id, estacao_id, valor, data) values(nextval('seq_velocidade')," +
            IDESTACAO + "," + valor + ", '" + data + "' );";
        query.execute(sql);
    }

    public void insertRotacao(String valor, Date data) {
        String sql = "insert into rotacao (id, estacao_id, valor, data) values(nextval('seq_rotacao')," +
            IDESTACAO + "," + valor + ", '" + data + "' );";
        query.execute(sql);
    }

    public void insertChuva(String valor, Date data) {
        String sql = "insert into chuva (id, estacao_id, valor, data) values(nextval('seq_chuva')," +
            IDESTACAO + ",'" + valor + "', '" + data + "' );";
        query.execute(sql);
    }

    public void insertUmidade(String valor, Date data) {
        String sql = "insert into umidade (id, estacao_id, valor, data) values(nextval('seq_umidade')," +
            IDESTACAO + "," + valor + ", '" + data + "' );";
        query.execute(sql);
    }

    public void insertTemperatura(String valor, Date data) {
        String sql = "insert into temperatura (id, estacao_id, valor, data) values(nextval('seq_temperatura')," +
            IDESTACAO + "," + valor + ", '" + data + "' );";
        query.execute(sql);
    }

}
