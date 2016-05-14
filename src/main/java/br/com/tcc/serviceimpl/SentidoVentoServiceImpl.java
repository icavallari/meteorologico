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

import br.com.tcc.entidade.SentidoVento;
import br.com.tcc.model.VariacaoStringModel;
import br.com.tcc.service.Query;
import br.com.tcc.service.SentidoVentoService;
import br.com.tcc.util.Check;

@Named("ventoService")
public class SentidoVentoServiceImpl implements SentidoVentoService {

    @Inject
    private Query query;

    @Override
    public List<VariacaoStringModel> getVentosUltimaHora() {

        String sql = "select valor as valor, data as data from sentidovento where data::date = "
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
    public List<VariacaoStringModel> getVentosHoraDiaAnterior() {
        return getVentosHoraEspecificoDiasAtras(null);
    }

    @Override
    public List<VariacaoStringModel> getVentosHoraEspecificoDiasAtras(Integer diasAtras) {
        List<SentidoVento> dadosColetados = getVentosDia(diasAtras);

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
    public VariacaoStringModel getVentosDia() {
        return getVentosDiaEspecificoDiaAtras(null);
    }

    @Override
    public VariacaoStringModel getVentosDiaEspecificoDiaAtras(Integer diasAtras) {
        List<SentidoVento> dadosColetados = getVentosDia(diasAtras);

        if (dadosColetados.isEmpty()) {
            return new VariacaoStringModel();
        }

        // cria uma lista com os tipos cadastrados na base
        Set<String> tiposPossiveisDeVento = new HashSet<String>();
        for (SentidoVento vento : dadosColetados)
            tiposPossiveisDeVento.add(vento.getValor());

        // lista que armazena as ocorrências de uma hora
        List<String> lista = new ArrayList<String>();

        for (int i = 0; i < dadosColetados.size(); i++) {
            SentidoVento sv = dadosColetados.get(i);
            lista.add(sv.getValor());
        }

        return obterModaMediaMediana(lista, tiposPossiveisDeVento, dadosColetados.get(0).getData());

    }

    @Override
    public List<SentidoVento> getVentosDia(Integer diasAtras) {
        String sql;

        if (Check.naoNulo(diasAtras)) {
            sql =
                "select valor as valor, data as data from sentidovento where data::date = (now() - interval '" + diasAtras
                    + " day')::date order by data";
        } else {
            sql =
                "select valor as valor, data as data from sentidovento where data::date = (now() - interval '1 day')::date order by data";
        }
        return query.getListObject(new SentidoVento(), sql);
    }

    // TODO solução via sql para as 3 mais de um dia
    // select count(valor) as ocorrencias, valor from sentidovento where
    // data::date = (now() - interval '41 day')::date group by valor order by
    // ocorrencias desc limit 3

    private VariacaoStringModel obterModaMediaMediana(List<String> lista, Set<String> tiposPossiveis, Date data) {

        VariacaoStringModel vsm = new VariacaoStringModel();
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
                vsm.setModa(map.getKey());
                vsm.setQtdModa(map.getValue());
            } else if (i == 2) {
                vsm.setMedia(map.getKey());
                vsm.setQtdMedia(map.getValue());
            } else if (i == 3) {
                vsm.setMediana(map.getKey());
                vsm.setQtd(map.getValue());
            }
        }
        vsm.setData(data);
        return vsm;

    }

    public Integer getHora(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public List<VariacaoStringModel> getVentosUltimasHoras(Integer ultimasHoras) {

        return query.getListObject(new VariacaoStringModel(),
            "select data as data, sentidovento_moda1 as moda, sentidovento_moda2 as mediana, sentidovento_moda3 as media,"
                + "sentidovento_qtd_moda1 as qtdmoda, sentidovento_qtd_moda2 as qtdmediana, sentidovento_qtd_moda3 as qtd "
                + "from medicao_hora "
                + "order by data offset coalesce(0,(select count(*) - " + ultimasHoras + " from medicao_hora)) limit " + ultimasHoras);

    }

}
