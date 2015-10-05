package br.com.tcc.jobs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
 * classe responsavel por conectar com a base de dados local, consultando em
 * cada tabela a media de variações por hora armazenando em "medicao_hora" e a
 * media de variações ao longo do dia armazenando em "medicoes_dia"
 *
 * @author Rodrigo
 *
 */

@Configuration
@EnableAsync
@EnableScheduling
@Named
public class JobTabelasAuxiliares {

    @Inject
    private Query                     query;

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

    /**
     * executa toda hora às *:59:40
     */
    @Scheduled(cron = "40 59 * * * *")
    public void insereHorasNaTabelaHora() {

        TabelaAuxiliar tabelaHora;
        Boolean deveContinuar = true;
        List<TabelaAuxiliar> horas = new ArrayList<TabelaAuxiliar>();

        for (VariacaoStringModel vsm : sentidoVentoService.getVentosUltimaHora()) {
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
            }
        }

        if (deveContinuar) {
            List<VariacaoStringModel> chuvas = chuvaService.getUltimaHora();
            if (!chuvas.isEmpty()) {
                for (int i = 0; i < horas.size(); i++) {

                    // estão no mesmo intervalo de tempo (mesma hora de captura)
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

            List<VariacaoNumberModel> temperaturas = temperaturaService.getUltimaHora();
            if (!temperaturas.isEmpty()) {
                for (int i = 0; i < horas.size(); i++) {

                    // estão no mesmo intervalo de tempo (mesma hora de captura)
                    if (Check.mesmaData(temperaturas.get(i).getData(), horas.get(i).getData())) {
                        horas.get(i).setMaxTemp(new BigDecimal(temperaturas.get(i).getMax()));
                        horas.get(i).setMedTemp(new BigDecimal(temperaturas.get(i).getMed()));
                        horas.get(i).setMinTemp(new BigDecimal(temperaturas.get(i).getMin()));
                    }
                }
            }

            List<VariacaoNumberModel> umidades = umidadeService.getUltimaHora();
            if (!umidades.isEmpty()) {
                for (int i = 0; i < horas.size(); i++) {

                    // estão no mesmo intervalo de tempo (mesma hora de captura)
                    if (Check.mesmaData(umidades.get(i).getData(), horas.get(i).getData())) {
                        horas.get(i).setMaxUmid(new BigDecimal(umidades.get(i).getMax()));
                        horas.get(i).setMedUmid(new BigDecimal(umidades.get(i).getMed()));
                        horas.get(i).setMinUmid(new BigDecimal(umidades.get(i).getMin()));
                    }
                }
            }

            List<VariacaoNumberModel> rotacoes = rotacoesService.getUltimaHora();
            if (!rotacoes.isEmpty()) {
                for (int i = 0; i < horas.size(); i++) {

                    // estão no mesmo intervalo de tempo (mesma hora de captura)
                    if (Check.mesmaData(rotacoes.get(i).getData(), horas.get(i).getData())) {
                        horas.get(i).setMaxRota(new BigDecimal(rotacoes.get(i).getMax()));
                        horas.get(i).setMedRota(new BigDecimal(rotacoes.get(i).getMed()));
                        horas.get(i).setMinRota(new BigDecimal(rotacoes.get(i).getMin()));
                    }
                }
            }

            List<VariacaoNumberModel> velocidades = velocidadeVentoService.getUltimaHora();
            if (!velocidades.isEmpty()) {
                for (int i = 0; i < horas.size(); i++) {

                    // estão no mesmo intervalo de tempo (mesma hora de captura)
                    if (Check.mesmaData(velocidades.get(i).getData(), horas.get(i).getData())) {
                        horas.get(i).setMaxVelo(new BigDecimal(velocidades.get(i).getMax()));
                        horas.get(i).setMedVelo(new BigDecimal(velocidades.get(i).getMed()));
                        horas.get(i).setMinVelo(new BigDecimal(velocidades.get(i).getMin()));
                    }
                }
            }

            List<VariacaoNumberModel> pressoes = pressaoAtmosfericaService.getUltimaHora();
            if (!pressoes.isEmpty()) {
                for (int i = 0; i < horas.size(); i++) {

                    // estão no mesmo intervalo de tempo (mesma hora de captura)
                    if (Check.mesmaData(pressoes.get(i).getData(), horas.get(i).getData())) {
                        horas.get(i).setMaxPres(Integer.parseInt(pressoes.get(i).getMax()));
                        horas.get(i).setMedPres(Integer.parseInt(pressoes.get(i).getMed()));
                        horas.get(i).setMinPres(Integer.parseInt(pressoes.get(i).getMin()));
                    }

                }
            }

            String sql = "insert into medicao_hora (estacao_id, data, "
                // + "altitude_minima, altitude_media, altitude_maxima,"
                + "chuva_moda3, chuva_moda2, chuva_moda1,"
                + "pressao_minima, pressao_media, pressao_maxima,"
                + "rotacao_minima, rotacao_media, rotacao_maxima,"
                + "velocidade_minima, velocidade_media, velocidade_maxima,"
                + "temperatura_minima, temperatura_media, temperatura_maxima,"
                + "umidade_minima, umidade_media, umidade_maxima,"
                + "sentidovento_moda3, sentidovento_moda2, sentidovento_moda1,"
                + "sentidovento_qtd_moda3, sentidovento_qtd_moda2, sentidovento_qtd_moda1, "
                + "chuva_qtd_moda3, chuva_qtd_moda2, chuva_qtd_moda1) values ("
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

    @Scheduled(cron = "30 00 00 * * *")
    public void insereDiaNaTabelaDia() {

        TabelaAuxiliar tabelaDia = new TabelaAuxiliar();

        VariacaoStringModel vsmVento = sentidoVentoService.getVentosDia();
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

            VariacaoNumberModel vnmTemperatura = temperaturaService.getTemperaturasDiaAnterior();
            tabelaDia.setMaxTemp(new BigDecimal(vnmTemperatura.getMax()));
            tabelaDia.setMedTemp(new BigDecimal(vnmTemperatura.getMed()));
            tabelaDia.setMinTemp(new BigDecimal(vnmTemperatura.getMin()));
            tabelaDia.setData(vnmTemperatura.getData());

            VariacaoNumberModel vsmUmidade = umidadeService.getUmidadeDiaAnterior();
            tabelaDia.setMaxUmid(new BigDecimal(vsmUmidade.getMax()));
            tabelaDia.setMedUmid(new BigDecimal(vsmUmidade.getMed()));
            tabelaDia.setMinUmid(new BigDecimal(vsmUmidade.getMin()));
            tabelaDia.setData(vsmUmidade.getData());

            VariacaoNumberModel vsmVelocidade = velocidadeVentoService.getVelocidadesDiaAnterior();
            tabelaDia.setMaxVelo(new BigDecimal(vsmVelocidade.getMax()));
            tabelaDia.setMedVelo(new BigDecimal(vsmVelocidade.getMed()));
            tabelaDia.setMinVelo(new BigDecimal(vsmVelocidade.getMin()));
            tabelaDia.setData(vsmVelocidade.getData());

            VariacaoNumberModel vsmRotacao = rotacoesService.getRotacaoDiaAnterior();
            tabelaDia.setMaxRota(new BigDecimal(vsmRotacao.getMax()));
            tabelaDia.setMedRota(new BigDecimal(vsmRotacao.getMed()));
            tabelaDia.setMinRota(new BigDecimal(vsmRotacao.getMin()));
            tabelaDia.setData(vsmRotacao.getData());

            VariacaoNumberModel vsmPressao = pressaoAtmosfericaService.getPressoesDiaAnterior();
            tabelaDia.setMaxPres(Integer.parseInt(vsmPressao.getMax()));
            tabelaDia.setMedPres(Integer.parseInt(vsmPressao.getMed()));
            tabelaDia.setMinPres(Integer.parseInt(vsmPressao.getMin()));
            tabelaDia.setData(vsmPressao.getData());

            String sql = "insert into medicao_dia (estacao_id, data, "
                // + "altitude_minima, altitude_media, altitude_maxima,"
                + "chuva_moda3, chuva_moda2, chuva_moda1,"
                + "pressao_minima, pressao_media, pressao_maxima,"
                + "rotacao_minima, rotacao_media, rotacao_maxima,"
                + "velocidade_minima, velocidade_media, velocidade_maxima,"
                + "temperatura_minima, temperatura_media, temperatura_maxima,"
                + "umidade_minima, umidade_media, umidade_maxima,"
                + "sentidovento_moda3, sentidovento_moda2, sentidovento_moda1,"
                + "sentidovento_qtd_moda3, sentidovento_qtd_moda2, sentidovento_qtd_moda1, "
                + "chuva_qtd_moda3, chuva_qtd_moda2, chuva_qtd_moda1) values ("
                + "%d,'%s',"
                + "'%s','%s','%s',"
                + "%s,%s,%s,"
                + "%s,%s,%s,"
                + "%s,%s,%s,"
                + "%s,%s,%s,"
                + "%s,%s,%s,"
                + "'%s','%s','%s', %d, %d, %d, %d, %d, %d);";

            query.execute(String.format(sql, 1, tabelaDia.getData(),
                tabelaDia.getMedianaChuva(), tabelaDia.getMediaChuva(), tabelaDia.getModaChuva(),
                tabelaDia.getMinPres(), tabelaDia.getMedPres(), tabelaDia.getMaxPres(),
                tabelaDia.getMinRota(), tabelaDia.getMedRota(), tabelaDia.getMaxRota(),
                tabelaDia.getMinVelo(), tabelaDia.getMedVelo(), tabelaDia.getMaxVelo(),
                tabelaDia.getMinTemp(), tabelaDia.getMedTemp(), tabelaDia.getMaxTemp(),
                tabelaDia.getMinUmid(), tabelaDia.getMedUmid(), tabelaDia.getMaxUmid(),
                tabelaDia.getMedianaVento(), tabelaDia.getMediaVento(), tabelaDia.getModaVento(),
                tabelaDia.getQtdVento(), tabelaDia.getQtdVentoMedia(), tabelaDia.getQtdVentoModa(),
                tabelaDia.getQtdChuva(), tabelaDia.getQtdChuvaMedia(), tabelaDia.getQtdChuvaModa()
                ));

        }

    }

    @Scheduled(cron = "45 00 00 * * *")
    public void deletarDados30Dias() {
        query.execute("delete from medicao_hora where data::date < (now() - interval '30 day')::date");
        query.execute("delete from medicao_dia where data::date < (now() - interval '5 year')::date");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> tabela auxiliar concluida");
    }

}
