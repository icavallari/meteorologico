package br.com.tcc.teste;

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

import org.junit.Ignore;

import br.com.tcc.entidade.Chuva;
import br.com.tcc.entidade.SentidoVento;
import br.com.tcc.service.ChuvaService;
import br.com.tcc.service.SentidoVentoService;
import br.com.tcc.util.Check;

@Ignore
public class Tests extends SpringTest {

    @Inject
    private ChuvaService        chuvaService;

    @Inject
    private SentidoVentoService ventoService;

    public void agruparVentoDiaTeste() {
        List<SentidoVento> dadosColetados = ventoService.getVentosDia(39);

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

        agrupar(lista, tiposPossiveisDeVento, dadosColetados.get(0).getData());

    }

    public void agruparVentoHoraTeste() {

        List<SentidoVento> dadosColetados =
            ventoService.getVentosDia(35);

        System.out.println(" Varrendo a data : " +
            dadosColetados.get(0).getData());

        if (Check.naoNulo(dadosColetados)) {

            // inicialização das horas
            Integer horaAnterior = getHora(dadosColetados.get(0).getData());
            Integer horaAtual = horaAnterior;

            // cria uma lista com os tipos cadastrados na base
            // "talvez desnecessário"
            Set<String> tiposPossiveisDeVento = new HashSet<String>();
            for (SentidoVento vento : dadosColetados)
                tiposPossiveisDeVento.add(vento.getValor());

            // lista que armazena as ocorrências de uma hora
            List<String> lista = new ArrayList<String>();

            for (int i = 0; i < dadosColetados.size(); i++) {

                SentidoVento sv = dadosColetados.get(i);

                horaAtual = getHora(sv.getData());

                // contagem por hora
                if (horaAnterior.equals(horaAtual)) {

                    lista.add(sv.getValor());
                    horaAnterior = horaAtual;

                    if (i == dadosColetados.size() - 1) {

                        System.out.println("------------------ " + horaAnterior +
                            "horas ------------------");
                        agrupar(lista, tiposPossiveisDeVento, sv.getData());

                    }

                } else {
                    System.out.println("------------------ " + horaAnterior +
                        "horas ------------------");

                    agrupar(lista, tiposPossiveisDeVento, sv.getData());

                    horaAnterior = horaAtual;
                    lista = new ArrayList<String>();
                    lista.add(sv.getValor());

                }
            }
        } else {
            System.err.println(" Não houve registro nesse dia. ");
        }
    }

    public void agruparChuvaHoraTeste() {

        List<Chuva> dadosColetados =
            chuvaService.getChuvasDia(46);

        if (Check.naoNulo(dadosColetados)) {

            // inicialização das horas
            Integer horaAnterior = getHora(dadosColetados.get(0).getData());
            Integer horaAtual = horaAnterior;

            // cria uma lista com os tipos cadastrados na base
            // "talvez desnecessário"
            Set<String> tiposPossiveisDeChuva = new HashSet<String>();
            for (Chuva chuva : dadosColetados)
                tiposPossiveisDeChuva.add(chuva.getValor());

            // lista que armazena as ocorrências de uma hora
            List<String> lista = new ArrayList<String>();

            for (int i = 0; i < dadosColetados.size(); i++) {

                Chuva c = dadosColetados.get(i);

                horaAtual = getHora(c.getData());

                // contagem por hora
                if (horaAnterior.equals(horaAtual)) {

                    lista.add(c.getValor());
                    horaAnterior = horaAtual;

                    if (i == dadosColetados.size() - 1) {

                        // System.out.println("------------------ " +
                        // horaAnterior + " ------------------");
                        agrupar(lista, tiposPossiveisDeChuva, c.getData());

                    }

                } else {
                    // System.out.println("------------------ " + horaAnterior +
                    // " ------------------");

                    agrupar(lista, tiposPossiveisDeChuva, c.getData());

                    horaAnterior = horaAtual;
                    lista = new ArrayList<String>();
                    lista.add(c.getValor());

                }
            }
        } else {
            System.err.println(" Não houve registro nesse dia. ");
        }

    }

    private void agrupar(List<String> lista, Set<String> tiposPossiveis, Date data) {
        Map<String, Integer> frequencias = new TreeMap<String, Integer>();

        // verificar as frequencias de cada ocorrencia no intervalo
        // de
        // uma hora
        for (String tipo : tiposPossiveis) {
            Integer freq = Collections.frequency(lista, tipo);
            if (freq != 0) {
                // System.out.println(tipo + " possui " + freq);
                frequencias.put(tipo, freq);
            }
        }

        // pegar os 3 que mais apareceram e inserir no banco junto
        // com a hora na tabela auxiliar

        // System.out.println("---------------- ordenações ");

        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(frequencias.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {// ordenando por ordem decrescente
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // apenas os 3 primeiros serão inseridos
        int i = 0;
        String moda = "", mediana = "", minima = "";
        for (Entry<String, Integer> map : list) {
            ++i;
            if (i == 1) {
                moda = map.getKey();
            } else if (i == 2) {
                mediana = map.getKey();
            } else if (i == 3) {
                minima = map.getKey();
            }
        }

        System.out.println("insert into tabela_hora (maxima, media, minima) values(" + moda + "," + mediana + "," + minima + "," + data
            + ")");

    }

    public Integer getHora(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

}
