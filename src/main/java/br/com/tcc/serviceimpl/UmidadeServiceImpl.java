package br.com.tcc.serviceimpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tcc.model.ChartSimpleModel;
import br.com.tcc.model.ModalSimplesModel;
import br.com.tcc.model.VariacaoNumberModel;
import br.com.tcc.service.Query;
import br.com.tcc.service.UmidadeService;
import br.com.tcc.util.Check;

@Named("umidadeService")
public class UmidadeServiceImpl implements UmidadeService {

    @Inject
    private Query query;

    @Override
    public List<ChartSimpleModel> getUmidadesTodosOsDias() {

        // traz apenas do mes 4 por enquanto
        String sql = "select umidade_minima as minima, umidade_media as media, umidade_maxima as maxima, "
            + "data as data from medicao_dia order by data";

        return query.getListObject(new ChartSimpleModel(), sql);
    }

    @Override
    public List<VariacaoNumberModel> getUltimaHora(){
        String sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from umidade "
            + "where data::date = (now())::date and extract('hour' from data) = extract('hour' from now()) group by extract('hour' from data) order by data";
        return query.getListObject(new VariacaoNumberModel(), sql);
    }

    @Override
    public List<VariacaoNumberModel> getUmidadesHorasDiaAnterior() {
        return getUmidadesHoraEspecificoDiasAtras(null);
    }

    @Override
    public List<VariacaoNumberModel> getUmidadesHoraEspecificoDiasAtras(Integer diasAtras) {
        String sql;
        if (Check.naoNulo(diasAtras)) {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from umidade "
                +
                "where data::date = (now() - interval '" + diasAtras + " day')::date group by extract('hour' from data) order by data";
        } else {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from umidade "
                +
                "where data::date = (now() - interval '1 day')::date group by extract('hour' from data) order by data";
        }

        return query.getListObject(new VariacaoNumberModel(), sql);

    }

    @Override
    public VariacaoNumberModel getUmidadeDiaAnterior() {
        return getUmidadeEspecificoDiasAtras(null);
    }

    @Override
    public VariacaoNumberModel getUmidadeEspecificoDiasAtras(Integer diasAtras) {
        String sql;
        if (Check.naoNulo(diasAtras)) {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from umidade "
                +
                "where data::date = (now() - interval '" + diasAtras + " day')::date";
        } else {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from umidade "
                +
                "where data::date = (now() - interval '1 day')::date";
        }
        return query.getListObject(new VariacaoNumberModel(), sql).get(0);
    }

    @Override
    public List<ModalSimplesModel> getUltimasHoras(Integer ultimasHoras) {

        return query.getListObject(new ModalSimplesModel(),
            "select umidade_minima as min, umidade_media as med, umidade_maxima as max, data as data "
                + "from medicao_hora "
                + "order by data offset coalesce(0,(select count(*) - " + ultimasHoras + " from medicao_hora)) limit " + ultimasHoras);

    }

}
