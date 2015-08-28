package br.com.tcc.serviceimpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tcc.model.ChartSimpleModel;
import br.com.tcc.model.ModalSimplesModel;
import br.com.tcc.model.VariacaoNumberModel;
import br.com.tcc.service.PressaoAtmosfericaService;
import br.com.tcc.service.Query;
import br.com.tcc.util.Check;

@Named("pressaoAtmosfericaService")
public class PressaoAtmosfericaServiceImpl implements PressaoAtmosfericaService {

    @Inject
    private Query query;

    @Override
    public List<ChartSimpleModel> getPressoesTodosOsDias() {

        // traz apenas do mes 4 por enquanto
        String sql = "select pressao_minima as minima, pressao_media as media, pressao_maxima as maxima, "
            + "data as data from medicao_dia order by data";

        return query.getListObject(new ChartSimpleModel(), sql);
    }

    @Override
    public List<VariacaoNumberModel> getUltimaHora(){
        String sql = "select round(max(valor),0) as max, round(avg(valor),0) as med, round(min(valor),0) as min, min(data) as data from pressaoatmosferica "
            + "where data::date = (now())::date and extract('hour' from data) = extract('hour' from now()) group by extract('hour' from data) order by data";
        return query.getListObject(new VariacaoNumberModel(), sql);
    }

    @Override
    public List<VariacaoNumberModel> getPressoesHorasDiaAnterior() {
        return getPressoesHoraEspecificoDiasAtras(null);
    }

    @Override
    public List<VariacaoNumberModel> getPressoesHoraEspecificoDiasAtras(Integer diasAtras) {
        String sql;
        if (Check.naoNulo(diasAtras)) {
            sql = "select round(max(valor),0) as max, round(avg(valor),0) as med, round(min(valor),0) as min, min(data) as data from pressaoatmosferica "
                +
                "where data::date = (now() - interval '" + diasAtras + " day')::date group by extract('hour' from data) order by data";
        } else {
            sql = "select round(max(valor),0) as max, round(avg(valor),0) as med, round(min(valor),0) as min, min(data) as data from pressaoatmosferica "
                +
                "where data::date = (now() - interval '1 day')::date group by extract('hour' from data) order by data";
        }

        return query.getListObject(new VariacaoNumberModel(), sql);

    }

    @Override
    public VariacaoNumberModel getPressoesDiaAnterior() {
        return getPressoesEspecificoDiaAnterior(null);
    }

    @Override
    public VariacaoNumberModel getPressoesEspecificoDiaAnterior(Integer diasAtras) {
        String sql;

        if (Check.naoNulo(diasAtras)) {
            sql = "select round(max(valor),0) as max, round(avg(valor),0) as med, round(min(valor),0) as min, min(data) as data from pressaoatmosferica "
                +
                "where data::date = (now() - interval '" + diasAtras + " day')::date";
        } else {
            sql = "select round(max(valor),0) as max, round(avg(valor),0) as med, round(min(valor),0) as min, min(data) as data from pressaoatmosferica "
                +
                "where data::date = (now() - interval '1 day')::date";
        }

        return query.getListObject(new VariacaoNumberModel(), sql).get(0);
    }

    @Override
    public List<ModalSimplesModel> getUltimasHoras(Integer ultimasHoras) {

        return query.getListObject(new ModalSimplesModel(),
            "select pressao_minima as min, pressao_media as med, pressao_maxima as max, data as data "
                + "from medicao_hora "
                + "order by data offset (select count(*) - " + ultimasHoras + " from medicao_hora) limit " + ultimasHoras);

    }

}
