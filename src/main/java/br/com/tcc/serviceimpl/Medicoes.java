package br.com.tcc.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tcc.model.TabelaAuxiliar;
import br.com.tcc.model.VariacaoNumberModel;
import br.com.tcc.model.VariacaoStringModel;
import br.com.tcc.service.ChuvaService;
import br.com.tcc.service.PressaoAtmosfericaService;
import br.com.tcc.service.Query;
import br.com.tcc.service.RotacoesService;
import br.com.tcc.service.SentidoVentoService;
import br.com.tcc.service.TemperaturaService;
import br.com.tcc.service.UmidadeService;
import br.com.tcc.service.VelocidadeVentoService;
import br.com.tcc.util.Check;

/**
 * Responsavel por limpar as tabelas de medições (mediçao dia e medicao hora) e reinserir
 *
 * @see ao subir a aplicação após criar as tabelas e jobs auxiliares é
 *      necessário obter as variações dos dados até então já armazenados
 * @author Rodrigo
 *
 */
@Named
public class Medicoes {

    @Inject
    private ChuvaService              chuvaService;

    @Inject
    private UmidadeService            umidadeService;

    @Inject
    private RotacoesService           rotacoesService;

    @Inject
    private TemperaturaService        temperaturaService;

    @Inject
    private SentidoVentoService       sentidoVentoService;

    @Inject
    private VelocidadeVentoService    velocidadeVentoService;

    @Inject
    private PressaoAtmosfericaService pressaoAtmosfericaService;

    @Inject
    private Query                     query;

    private Integer                   DIAS_ = 115;

    public void insereHorasNaTabelaHora() {

        // zera a tabela de medicao_hora
        query.execute("delete from medicao_hora");

        // dias entre a maior e a menor data de captura no banco de dados
        // deve-se deletar os dados da tabela medicao_dia
        // select extract(epoch from ( select age(min(data)::date, now()::date)
        // from altitude )) / (60 * 60 *24 )

        TabelaAuxiliar tabelaHora;

        for (int j = DIAS_; j > 0; j--) {

            Boolean deveContinuar = true;
            List<TabelaAuxiliar> horas = new ArrayList<TabelaAuxiliar>();

            for (VariacaoStringModel vsm : sentidoVentoService.getVentosHoraEspecificoDiasAtras(j)) {
                tabelaHora = new TabelaAuxiliar();
                if (Check.naoNulo(vsm.getData())) {
                    tabelaHora.setModaVento(vsm.getModa());
                    tabelaHora.setQtdVentoModa(vsm.getQtdModa());

                    tabelaHora.setMediaVento(vsm.getMedia());
                    tabelaHora.setQtdVentoMedia(vsm.getQtdMedia());

                    tabelaHora.setMedianaVento(vsm.getMediana());
                    tabelaHora.setQtdVento(vsm.getQtd());

                    tabelaHora.setData(vsm.getData());
                    horas.add(tabelaHora);
                } else {
                    deveContinuar = false;
                    break;
                }
            }
            if (deveContinuar) {
                List<VariacaoStringModel> chuvas = chuvaService.getChuvasHorasDiaAnteriorEspecificoDiaAtras(j);

                if (!chuvas.isEmpty()) {
                    for (int i = 0; i < horas.size(); i++) {

                        // estão no mesmo intervalo de tempo (mesma hora de
                        // captura)
                        if (Check.mesmaData(chuvas.get(i).getData(), horas.get(i).getData())) {
                            horas.get(i).setModaChuva(chuvas.get(i).getModa());
                            horas.get(i).setQtdChuvaModa(chuvas.get(i).getQtdModa());

                            horas.get(i).setMediaChuva(chuvas.get(i).getMedia());
                            horas.get(i).setQtdChuvaMedia(chuvas.get(i).getQtdMedia());

                            horas.get(i).setMedianaChuva(chuvas.get(i).getMediana());
                            horas.get(i).setQtdChuva(chuvas.get(i).getQtd());
                        }

                    }
                }

                List<VariacaoNumberModel> temperaturas = temperaturaService.getTemperaturasHoraEspecificoDiasAtras(j);
                if (!temperaturas.isEmpty()) {
                    for (int i = 0; i < horas.size(); i++) {

                        // estão no mesmo intervalo de tempo (mesma hora de
                        // captura)
                        if (Check.mesmaData(temperaturas.get(i).getData(), horas.get(i).getData())) {
                            horas.get(i).setMaxTemp(new BigDecimal(temperaturas.get(i).getMax()));
                            horas.get(i).setMedTemp(new BigDecimal(temperaturas.get(i).getMed()));
                            horas.get(i).setMinTemp(new BigDecimal(temperaturas.get(i).getMin()));
                        }

                    }
                }

                List<VariacaoNumberModel> umidades = umidadeService.getUmidadesHoraEspecificoDiasAtras(j);
                if (!umidades.isEmpty()) {
                    for (int i = 0; i < horas.size(); i++) {

                        // estão no mesmo intervalo de tempo (mesma hora de
                        // captura)
                        if (Check.mesmaData(umidades.get(i).getData(), horas.get(i).getData())) {
                            horas.get(i).setMaxUmid(new BigDecimal(umidades.get(i).getMax()));
                            horas.get(i).setMedUmid(new BigDecimal(umidades.get(i).getMed()));
                            horas.get(i).setMinUmid(new BigDecimal(umidades.get(i).getMin()));
                        }

                    }
                }

                List<VariacaoNumberModel> rotacoes = rotacoesService.getRotacoesHoraEspecificoDiasAtras(j);
                if (!rotacoes.isEmpty()) {
                    for (int i = 0; i < horas.size(); i++) {

                        // estão no mesmo intervalo de tempo (mesma hora de
                        // captura)
                        if (Check.mesmaData(rotacoes.get(i).getData(), horas.get(i).getData())) {
                            horas.get(i).setMaxRota(new BigDecimal(rotacoes.get(i).getMax()));
                            horas.get(i).setMedRota(new BigDecimal(rotacoes.get(i).getMed()));
                            horas.get(i).setMinRota(new BigDecimal(rotacoes.get(i).getMin()));
                        }

                    }
                }

                List<VariacaoNumberModel> velocidades = velocidadeVentoService.getVelocidadesHoraEspecificoDiasAtras(j);
                if (!velocidades.isEmpty()) {
                    for (int i = 0; i < horas.size(); i++) {

                        // estão no mesmo intervalo de tempo (mesma hora de
                        // captura)
                        if (Check.mesmaData(velocidades.get(i).getData(), horas.get(i).getData())) {
                            horas.get(i).setMaxVelo(new BigDecimal(velocidades.get(i).getMax()));
                            horas.get(i).setMedVelo(new BigDecimal(velocidades.get(i).getMed()));
                            horas.get(i).setMinVelo(new BigDecimal(velocidades.get(i).getMin()));
                        }

                    }
                }

                List<VariacaoNumberModel> pressoes = pressaoAtmosfericaService.getPressoesHoraEspecificoDiasAtras(j);
                if (!pressoes.isEmpty()) {
                    for (int i = 0; i < horas.size(); i++) {

                        // estão no mesmo intervalo de tempo (mesma hora de
                        // captura)
                        if (Check.mesmaData(pressoes.get(i).getData(), horas.get(i).getData())) {
                            horas.get(i).setMaxPres(Integer.parseInt(pressoes.get(i).getMax()));
                            horas.get(i).setMedPres(Integer.parseInt(pressoes.get(i).getMed()));
                            horas.get(i).setMinPres(Integer.parseInt(pressoes.get(i).getMin()));
                        }

                    }
                }

                String sql = "insert into medicao_hora (estacao_id, data, "
                    // + "altitude_minima, altitude_media, altitude_maxima,"
                    + "chuva, chuva_mediana, chuva_moda,"
                    + "pressao_minima, pressao_media, pressao_maxima,"
                    + "rotacao_minima, rotacao_media, rotacao_maxima,"
                    + "velocidade_minima, velocidade_media, velocidade_maxima,"
                    + "temperatura_minima, temperatura_media, temperatura_maxima,"
                    + "umidade_minima, umidade_media, umidade_maxima,"
                    + "sentidovento, sentidovento_mediana, sentidovento_moda,"
                    + "sentidovento_qtd, sentidovento_qtdmediana, sentidovento_qtdmoda, "
                    + "chuva_qtd, chuva_qtdmediana, chuva_qtdmoda) values ("
                    + "%d,'%s',"
                    + "'%s','%s','%s',"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "'%s','%s','%s', %d, %d, %d, %d, %d, %d);";

                for (TabelaAuxiliar ta : horas) {

                    query.execute(String.format(sql, 1, ta.getData(),
                        ta.getMedianaChuva(), ta.getMediaChuva(), ta.getModaChuva(),
                        ta.getMinPres(), ta.getMedPres(), ta.getMaxPres(),
                        ta.getMinRota(), ta.getMedRota(), ta.getMaxRota(),
                        ta.getMinVelo(), ta.getMedVelo(), ta.getMaxVelo(),
                        ta.getMinTemp(), ta.getMedTemp(), ta.getMaxTemp(),
                        ta.getMinUmid(), ta.getMedUmid(), ta.getMaxUmid(),
                        ta.getMedianaVento(), ta.getMediaVento(), ta.getModaVento(),
                        ta.getQtdVento(), ta.getQtdVentoMedia(), ta.getQtdVentoModa(),
                        ta.getQtdChuva(), ta.getQtdChuvaMedia(), ta.getQtdChuvaModa()));

                }
            }
        }
    }

    public void insereDiaNaTabelaDia() {

        // dias entre a maior e a menor data de captura no banco de dados
        // deve-se deletar os dados da tabela medicao_dia
        // select extract(epoch from ( select age(min(data)::date, now()::date)
        // from altitude )) / (60 * 60 *24 )
        // zera a tabela de medicao_dia
        query.execute("delete from medicao_dia");

        TabelaAuxiliar tabelaDia;

        for (int i = DIAS_; i > 0; i--) {

            tabelaDia = new TabelaAuxiliar();

            VariacaoStringModel vsmVento = sentidoVentoService.getVentosDiaEspecificoDiaAtras(i);
            if (Check.naoNulo(vsmVento.getData())) {
                tabelaDia.setModaVento(vsmVento.getModa());
                tabelaDia.setQtdVentoModa(vsmVento.getQtdModa());

                tabelaDia.setMediaVento(vsmVento.getMedia());
                tabelaDia.setQtdVentoMedia(vsmVento.getQtdMedia());

                tabelaDia.setMedianaVento(vsmVento.getMediana());
                tabelaDia.setQtdVento(vsmVento.getQtd());
                tabelaDia.setData(vsmVento.getData());

                VariacaoStringModel vsmChuva = chuvaService.getChuvasDiaAnterior();
                tabelaDia.setModaChuva(vsmChuva.getModa());
                tabelaDia.setQtdChuvaModa(vsmChuva.getQtdModa());

                tabelaDia.setMediaChuva(vsmChuva.getMedia());
                tabelaDia.setQtdVentoMedia(vsmChuva.getQtdMedia());

                tabelaDia.setMedianaChuva(vsmChuva.getMediana());
                tabelaDia.setQtdVento(vsmChuva.getQtd());
                tabelaDia.setData(vsmChuva.getData());

                VariacaoNumberModel vnmTemperatura = temperaturaService.getTemperaturasEspecificoDiaAnterior(i);
                tabelaDia.setMaxTemp(new BigDecimal(vnmTemperatura.getMax()));
                tabelaDia.setMedTemp(new BigDecimal(vnmTemperatura.getMed()));
                tabelaDia.setMinTemp(new BigDecimal(vnmTemperatura.getMin()));
                tabelaDia.setData(vnmTemperatura.getData());

                VariacaoNumberModel vsmUmidade = umidadeService.getUmidadeEspecificoDiasAtras(i);
                tabelaDia.setMaxUmid(new BigDecimal(vsmUmidade.getMax()));
                tabelaDia.setMedUmid(new BigDecimal(vsmUmidade.getMed()));
                tabelaDia.setMinUmid(new BigDecimal(vsmUmidade.getMin()));
                tabelaDia.setData(vsmUmidade.getData());

                VariacaoNumberModel vsmVelocidade = velocidadeVentoService.getVelocidadesEspecificoDiaAtras(i);
                tabelaDia.setMaxVelo(new BigDecimal(vsmVelocidade.getMax()));
                tabelaDia.setMedVelo(new BigDecimal(vsmVelocidade.getMed()));
                tabelaDia.setMinVelo(new BigDecimal(vsmVelocidade.getMin()));
                tabelaDia.setData(vsmVelocidade.getData());

                VariacaoNumberModel vsmRotacao = rotacoesService.getRotacaoEspecificoDiasAtras(i);
                tabelaDia.setMaxRota(new BigDecimal(vsmRotacao.getMax()));
                tabelaDia.setMedRota(new BigDecimal(vsmRotacao.getMed()));
                tabelaDia.setMinRota(new BigDecimal(vsmRotacao.getMin()));
                tabelaDia.setData(vsmRotacao.getData());

                VariacaoNumberModel vsmPressao = pressaoAtmosfericaService.getPressoesEspecificoDiaAnterior(i);
                tabelaDia.setMaxPres(Integer.parseInt(vsmPressao.getMax()));
                tabelaDia.setMedPres(Integer.parseInt(vsmPressao.getMed()));
                tabelaDia.setMinPres(Integer.parseInt(vsmPressao.getMin()));
                tabelaDia.setData(vsmPressao.getData());

                String sql = "insert into medicao_dia (estacao_id, data, "
                    // + "altitude_minima, altitude_media, altitude_maxima,"
                    + "chuva, chuva_mediana, chuva_moda,"
                    + "pressao_minima, pressao_media, pressao_maxima,"
                    + "rotacao_minima, rotacao_media, rotacao_maxima,"
                    + "velocidade_minima, velocidade_media, velocidade_maxima,"
                    + "temperatura_minima, temperatura_media, temperatura_maxima,"
                    + "umidade_minima, umidade_media, umidade_maxima,"
                    + "sentidovento, sentidovento_mediana, sentidovento_moda,"
                    + "sentidovento_qtd, sentidovento_qtdmediana, sentidovento_qtdmoda, "
                    + "chuva_qtd, chuva_qtdmediana, chuva_qtdmoda) values ("
                    + "%d,'%s',"
                    + "'%s','%s','%s',"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "%s,%s,%s,"
                    + "'%s','%s','%s', %d, %d, %d, %d, %d, %d);";

                // realiza o insert
                query.execute(String.format(sql, 1, tabelaDia.getData(),
                    tabelaDia.getMedianaChuva(), tabelaDia.getMediaChuva(),
                    tabelaDia.getModaChuva(),
                    tabelaDia.getMinPres(), tabelaDia.getMedPres(),
                    tabelaDia.getMaxPres(),
                    tabelaDia.getMinRota(), tabelaDia.getMedRota(),
                    tabelaDia.getMaxRota(),
                    tabelaDia.getMinVelo(), tabelaDia.getMedVelo(),
                    tabelaDia.getMaxVelo(),
                    tabelaDia.getMinTemp(), tabelaDia.getMedTemp(),
                    tabelaDia.getMaxTemp(),
                    tabelaDia.getMinUmid(), tabelaDia.getMedUmid(),
                    tabelaDia.getMaxUmid(),
                    tabelaDia.getMedianaVento(), tabelaDia.getMediaVento(),
                    tabelaDia.getModaVento(),
                    tabelaDia.getQtdVento(), tabelaDia.getQtdVentoMedia(), tabelaDia.getQtdVentoModa(),
                    tabelaDia.getQtdChuva(), tabelaDia.getQtdChuvaMedia(), tabelaDia.getQtdChuvaModa()));
            }
        }

    }

    public void deletarDados30Dias() {
        query.execute("delete from medicao_hora where data::date = (now() - interval '30 day')::date");
        query.execute("delete from medicao_dia where data::date = (now() - interval '5 year')::date");
    }

}
