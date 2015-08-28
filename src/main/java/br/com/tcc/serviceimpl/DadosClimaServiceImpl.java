package br.com.tcc.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tcc.model.DadosClimaModel;
import br.com.tcc.service.DadosClimaService;
import br.com.tcc.service.Query;

@Named("dadosClimaService")
public class DadosClimaServiceImpl implements DadosClimaService {

    @Inject
    private Query                query;

    private static final Integer IDESTACAO = 1;

    @Override
    public DadosClimaModel getUltimosValores() throws ParseException {

        //formatando para duas casas decimais postgres
        //to_char(max(valor), '999D99') as max

        String altitude = query.getSingleValue("select a.valor as altitude from altitude a order by data desc limit 1");
        String chuva = query.getSingleValue("select c.valor as chuva from chuva c order by data desc limit 1");
        String pressao = query.getSingleValue("select p.valor as pressao from pressaoatmosferica p order by data desc limit 1");
        String rotacao = query.getSingleValue("select r.valor as rotacao from rotacao r order by data desc limit 1");
        String vento = query.getSingleValue("select s.valor as sentidovento from sentidovento s order by data desc limit 1");
        String temperatura = query.getSingleValue("select t.valor as temperatura from temperatura t order by data desc limit 1");
        String umidade = query.getSingleValue("select u.valor as umidade from umidade u order by data desc limit 1");
        String velocidade = query.getSingleValue("select v.valor as velocidade from velocidade v order by data desc limit 1");
        String data = query.getSingleValue("select v.data from velocidade v order by data desc limit 1");

        DadosClimaModel estacaoAntigaModel = new DadosClimaModel();

        estacaoAntigaModel.setAltitude(altitude != null ? Float.parseFloat(altitude) : null);
        estacaoAntigaModel.setChuva(chuva != null ? chuva : null);
        estacaoAntigaModel.setPressao(pressao != null ? Float.parseFloat(pressao) : null);
        estacaoAntigaModel.setRotacao(rotacao != null ? Float.parseFloat(rotacao) : null);
        estacaoAntigaModel.setSentidoVento(vento != null ? vento : null);
        estacaoAntigaModel.setTemperatura(temperatura != null ? Float.parseFloat(temperatura) : null);
        estacaoAntigaModel.setUmidade(umidade != null ? Float.parseFloat(umidade) : null);
        estacaoAntigaModel.setVelocidade(velocidade != null ? Float.parseFloat(velocidade) : null);

        if (data != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            estacaoAntigaModel.setDataCaptura(sdf.parse(data));
        }

        return estacaoAntigaModel;
    }

    @Override
    public void insereDadosCaptados(String content) {
        String html = content.replaceAll("###", "").replace("null", "");

        String[] dadosSeparados = html.split(",");

        for (int i = 0; i < dadosSeparados.length; i++) {

            if (dadosSeparados[i].startsWith("p")) {
                insertPressao(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("a")) {
                insertAltitude(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("s")) {
                insertVento(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("v")) {
                insertVelocidade(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("r")) {
                insertRotacao(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("c")) {
                insertChuva(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("u")) {
                insertUmidade(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }

            if (dadosSeparados[i].startsWith("t")) {
                insertTemperatura(dadosSeparados[i].substring(1, dadosSeparados[i].length()));
            }
        }

    }

    /**
     * métodos para inserções
     */

    public void insertPressao(String valor) {
        String sql = "insert into pressaoatmosferica (estacao_id, valor, data) values(" +
            IDESTACAO + "," + valor + ", now());";
        query.execute(sql);
    }

    public void insertAltitude(String valor) {
        String sql = "insert into altitude (estacao_id, valor, data) values(" +
            IDESTACAO + "," + valor + ", now());";
        query.execute(sql);
    }

    public void insertVento(String valor) {
        String sql = "insert into sentidovento (estacao_id, valor, data) values(" +
            IDESTACAO + ",'" + valor + "', now());";
        query.execute(sql);
    }

    public void insertVelocidade(String valor) {
        String sql = "insert into velocidade (estacao_id, valor, data) values(" +
            IDESTACAO + "," + valor + ", now());";
        query.execute(sql);
    }

    public void insertRotacao(String valor) {
        String sql = "insert into rotacao (estacao_id, valor, data) values(" +
            IDESTACAO + "," + valor + ", now());";
        query.execute(sql);
    }

    public void insertChuva(String valor) {
        String sql = "insert into chuva (estacao_id, valor, data) values(" +
            IDESTACAO + ",'" + valor + "', now());";
        query.execute(sql);
    }

    public void insertUmidade(String valor) {
        String sql = "insert into umidade (estacao_id, valor, data) values(" +
            IDESTACAO + "," + valor + ", now());";
        query.execute(sql);
    }

    public void insertTemperatura(String valor) {
        String sql = "insert into temperatura (estacao_id, valor, data) values(" +
            IDESTACAO + "," + valor + ", now());";
        query.execute(sql);
    }

}
