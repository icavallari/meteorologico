package br.com.tcc.teste;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

/**
 * classe responsavel por gerar um script para o banco de dados para um
 * intervalo de datas especificado
 *
 * @author Rodrigo
 *
 */

@Ignore
public class CriarScriptsInsertTest {
    Random                       random    = new Random();

    List<String>                 chuvas    = Arrays.asList(
                                               "sem-chuva",
                                               "baixa-intensidade",
                                               "intensidade-moderada",
                                               "alta-intensidade");

    List<String>                 ventos    = Arrays.asList(
                                               "NORTE",
                                               "NOR-NORDESTE",
                                               "NORDESTE",
                                               "ES-NORDESTE",
                                               "LESTE",
                                               "ES-SUDESTE",
                                               "SUDESTE",
                                               "SU-SUDESTE",
                                               "SUL",
                                               "SU-SUDOESTE",
                                               "SUDOESTE",
                                               "OES-SUDOESTE",
                                               "OESTE",
                                               "OES-NOROESTE",
                                               "NOROESTE",
                                               "NOR-NOROESTE");

    private static final Integer IDESTACAO = 1;

    private static final Float   tempMin   = 10f;
    private static final Float   tempMax   = 42f;

    private static final Float   velMin    = 5f;
    private static final Float   velMax    = 120f;

    private static final Float   rotMin    = 1f;
    private static final Float   rotMax    = 13f;

    private static final Float   umiMin    = 20f;
    private static final Float   umiMax    = 60f;

    public Float format(float f) {
        DecimalFormat df = new DecimalFormat("#######");
        return Float.parseFloat(df.format(f));
    }

    @Test
    public void gerar() throws FileNotFoundException, UnsupportedEncodingException, ParseException {
        PrintWriter writer = new PrintWriter("insert.sql", "UTF-8");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String dataIni = "27/07/2015";
        String dataFim = "28/07/2015";

        Calendar c = new GregorianCalendar();
        c.setTime(sdf.parse(dataIni));

        do {
            c.add(Calendar.MINUTE, 1);

            writer.println("insert into pressaoatmosferica (estacao_id, valor, data) values(" +
                IDESTACAO + "," + random.nextInt(90000) + ", '" + c.getTime() + "' );");

            writer.println("insert into altitude (estacao_id, valor, data) values(" +
                IDESTACAO + ",455.61, '" + c.getTime() + "' );");

            writer.println("insert into sentidovento (estacao_id, valor, data) values(" +
                IDESTACAO + ",'" + ventos.get(random.nextInt(16)) + "', '" + c.getTime() + "' );");

            writer.println("insert into velocidade (estacao_id, valor, data) values(" +
                IDESTACAO + "," + format(random.nextFloat() * (velMax - velMin) + velMin) + ", '"
                + c.getTime() + "' );");

            writer.println("insert into rotacao (estacao_id, valor, data) values(" +
                IDESTACAO + "," + format(random.nextFloat() * (rotMax - rotMin) + rotMin) + ", '"
                + c.getTime() + "' );");

            writer.println("insert into chuva (estacao_id, valor, data) values(" +
                IDESTACAO + ",'" + chuvas.get(random.nextInt(3)) + "', '" + c.getTime() + "' );");

            writer.println("insert into umidade (estacao_id, valor, data) values(" +
                IDESTACAO + "," + format(random.nextFloat() * (umiMax - umiMin) + umiMin) + ", '"
                + c.getTime() + "' );");

            writer.println("insert into temperatura (estacao_id, valor, data) values(" +
                IDESTACAO + "," + format(random.nextFloat() * (tempMax - tempMin) + tempMin) + ", '"
                + c.getTime() + "' );");

        } while (c.getTime().before(sdf.parse(dataFim)));

        writer.close();
    }
}
