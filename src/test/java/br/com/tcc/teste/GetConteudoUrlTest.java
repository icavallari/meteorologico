package br.com.tcc.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;

/**
 * classe responsavel por consumir uma url lendo o seu html
 *
 * @author Rodrigo
 *
 */

@Ignore
public class GetConteudoUrlTest {

    private BufferedReader      in;
    private static final String URL = "http://172.16.7.162/";

    @Test
    public void t() throws Exception {
        // getContentUrl();
        System.out.println(doHttpConnection());
    }

    public String doHttpConnection() throws Exception {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try
        {
            // create the HttpURLConnection
            url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            // connection.setDoOutput(true);

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

    /**
     * buffered reader
     *
     * @throws IOException
     */
    public void getContentUrl() throws IOException {

        URL url = new URL(URL);
        in = new BufferedReader(
            new InputStreamReader(url.openStream()));

        String html = null;
        String linha;

        if (in.ready()) {

            while ((linha = in.readLine()) != null) {
                html += linha.replaceAll("\\<.*?>", "").trim();
            }

        }

        if (in != null) in.close();

        split(html);
    }

    public void split(String content) {
        String html = content.replaceAll("###", "");

        String[] dadosSeparados = html.split(",");

        for (int i = 0; i < dadosSeparados.length; i++) {

            if (dadosSeparados[i].startsWith("p")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("a")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("s")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("v")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("r")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("c")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("u")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("t")) {
                System.out.println(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

        }
    }
}
