package br.com.tcc.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tcc.entidade.Chuva;
import br.com.tcc.entidade.SentidoVento;
import br.com.tcc.model.ModalChuvaModel;
import br.com.tcc.model.VariacaoStringModel;
import br.com.tcc.service.ChuvaService;
import br.com.tcc.service.Query;
import br.com.tcc.util.Check;

@Named("chuvaService")
public class ChuvaServiceImpl implements ChuvaService {

    @Inject
    private Query              query;

    @Override
    public List<VariacaoStringModel> getUltimaHora() {

        String sql = "select valor as valor, data as data from chuva where data::date = "
            + "(now())::date and extract('hour' from data) = extract('hour' from now()) order by data";
        List<SentidoVento> dadosColetados = query.getListObject(new SentidoVento(), sql);

        if (dadosColetados.isEmpty()) {
            return new ArrayList<VariacaoStringModel>();
        }

        List<VariacaoStringModel> vsm = new ArrayList<VariacaoStringModel>();

        // inicialização das horas
        Integer horaAnterior = getHora(dadosColetados.get(0).getData());
        Integer horaAtual = horaAnterior;

        // cria uma lista com os tipos cadastrados na base
        Set<String> tiposPossiveis = new HashSet<String>();
        for (SentidoVento vento : dadosColetados)
            tiposPossiveis.add(vento.getValor());

        // lista que armazena as ocorrências de uma hora
        List<String> lista = new ArrayList<String>();
        Date menorDataHora = null;

        for (int i = 0; i < dadosColetados.size(); i++) {
            SentidoVento sv = dadosColetados.get(i);
            horaAtual = getHora(sv.getData());

            if (!Check.naoNulo(menorDataHora)) {
                menorDataHora = sv.getData();
            }

            // contagem por hora
            if (horaAnterior.equals(horaAtual)) {

                lista.add(sv.getValor());
                horaAnterior = horaAtual;

                if (i == dadosColetados.size() - 1) {
                    vsm.add(obterModaMediaMediana(lista, tiposPossiveis, menorDataHora));
                }

            } else {

                vsm.add(obterModaMediaMediana(lista, tiposPossiveis, menorDataHora));
                horaAnterior = horaAtual;
                lista = new ArrayList<String>();
                lista.add(sv.getValor());

                menorDataHora = sv.getData();

            }
        }

        return vsm;
    }

    @Override
    public List<VariacaoStringModel> getChuvasHorasDiaAnterior() {

        return getChuvasHorasDiaAnteriorEspecificoDiaAtras(null);
    }

    @Override
    public List<VariacaoStringModel> getChuvasHorasDiaAnteriorEspecificoDiaAtras(Integer diasAtras) {

        List<VariacaoStringModel> variacoesHora = new ArrayList<VariacaoStringModel>();
        List<Chuva> dadosColetados = getChuvasDia(diasAtras);

        if (dadosColetados.isEmpty()) {
            return new ArrayList<VariacaoStringModel>();
        }

        // inicialização das horas
        Integer horaAnterior = getHora(dadosColetados.get(0).getData());
        Integer horaAtual = horaAnterior;

        // cria uma lista com os tipos cadastrados na base
        Set<String> tiposPossiveis = new HashSet<String>();
        for (Chuva chuva : dadosColetados)
            tiposPossiveis.add(chuva.getValor());

        // lista que armazena as ocorrências de uma hora
        List<String> lista = new ArrayList<String>();
        Date menorDataHora = null;

        for (int i = 0; i < dadosColetados.size(); i++) {
            Chuva c = dadosColetados.get(i);
            horaAtual = getHora(c.getData());

            if (!Check.naoNulo(menorDataHora)) {
                menorDataHora = c.getData();
            }

            // contagem por hora
            if (horaAnterior.equals(horaAtual)) {

                lista.add(c.getValor());
                horaAnterior = horaAtual;

                if (i == dadosColetados.size() - 1) {
                    variacoesHora.add(obterModaMediaMediana(lista, tiposPossiveis, menorDataHora));
                }

            } else {

                variacoesHora.add(obterModaMediaMediana(lista, tiposPossiveis, menorDataHora));
                horaAnterior = horaAtual;
                lista = new ArrayList<String>();
                lista.add(c.getValor());

                menorDataHora = c.getData();

            }
        }

        return variacoesHora;
    }

    @Override
    public VariacaoStringModel getChuvasDiaAnterior() {
        return getChuvasDiaEspecificoDiaAtras(null);
    }

    @Override
    public VariacaoStringModel getChuvasDiaEspecificoDiaAtras(Integer diasAtras) {
        List<Chuva> dadosColetados = getChuvasDia(diasAtras);

        if (dadosColetados.isEmpty()) {
            return new VariacaoStringModel();
        }

        // cria uma lista com os tipos cadastrados na base
        Set<String> tiposPossiveis = new HashSet<String>();
        for (Chuva chuva : dadosColetados)
            tiposPossiveis.add(chuva.getValor());

        // lista que armazena as ocorrências de uma hora
        List<String> lista = new ArrayList<String>();

        for (int i = 0; i < dadosColetados.size(); i++) {
            Chuva c = dadosColetados.get(i);
            lista.add(c.getValor());
        }

        return obterModaMediaMediana(lista, tiposPossiveis, dadosColetados.get(0).getData());

    }

    @Override
    public List<Chuva> getChuvasDia(Integer diasAtras) {

        String sql;

        if (Check.naoNulo(diasAtras)) {
            sql =
                "select valor as valor, data as data from chuva where data::date = (now() - interval '" + diasAtras
                    + " day')::date order by data";
        } else {
            sql =
                "select valor as valor, data as data from chuva where data::date = (now() - interval '1 day')::date order by data";
        }
        return query.getListObject(new Chuva(), sql);
    }

    private VariacaoStringModel obterModaMediaMediana(List<String> lista, Set<String> tiposPossiveis, Date data) {
        VariacaoStringModel vdsm = new VariacaoStringModel();
        Map<String, Integer> frequencias = new TreeMap<String, Integer>();

        // verificar as frequencias de cada ocorrencia no intervalo de uma hora
        for (String tipo : tiposPossiveis) {
            Integer freq = Collections.frequency(lista, tipo);
            if (freq != 0) {
                frequencias.put(tipo, freq);
            }
        }

        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(frequencias.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {// ordenando por ordem decrescente
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // apenas os 3 primeiros sao captados
        int i = 0;
        for (Entry<String, Integer> map : list) {
            ++i;
            if (i == 1) {
                vdsm.setModa(map.getKey());
                vdsm.setQtdModa(map.getValue());
            } else if (i == 2) {
                vdsm.setMedia(map.getKey());
                vdsm.setQtdMedia(map.getValue());
            } else if (i == 3) {
                vdsm.setMediana(map.getKey());
                vdsm.setQtd(map.getValue());
            }
        }
        vdsm.setData(data);
        return vdsm;

    }

    public Integer getHora(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public List<ModalChuvaModel> getChuvaTemperaturaUltimasHoras(Integer ultimasHoras) {

        return query.getListObject(new ModalChuvaModel(),
            "select data as data, chuva_moda1 as chuva, temperatura_media as temperatura "
                + "from medicao_hora "
                + "order by data offset (select count(*) - " + ultimasHoras + " from medicao_hora) limit " + ultimasHoras);
    }

}
