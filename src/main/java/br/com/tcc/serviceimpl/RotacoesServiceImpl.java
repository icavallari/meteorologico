package br.com.tcc.serviceimpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tcc.model.VariacaoNumberModel;
import br.com.tcc.service.Query;
import br.com.tcc.service.RotacoesService;
import br.com.tcc.util.Check;

@Named("rotacoesService")
public class RotacoesServiceImpl implements RotacoesService {

    @Inject
    private Query query;

    @Override
    public List<VariacaoNumberModel> getRotacoesHorasDiaAnterior() {
        return getRotacoesHoraEspecificoDiasAtras(null);
    }

    @Override
    public List<VariacaoNumberModel> getUltimaHora(){
        String sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from rotacao "
            + "where data::date = (now())::date and extract('hour' from data) = extract('hour' from now()) group by extract('hour' from data) order by data";
        return query.getListObject(new VariacaoNumberModel(), sql);
    }

    @Override
    public List<VariacaoNumberModel> getRotacoesHoraEspecificoDiasAtras(Integer diasAtras) {
        String sql;
        if (Check.naoNulo(diasAtras)) {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from rotacao "
                +
                "where data::date = (now() - interval '" + diasAtras + " day')::date group by extract('hour' from data) order by data";
        } else {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from rotacao "
                +
                "where data::date = (now() - interval '1 day')::date group by extract('hour' from data) order by data";
        }

        return query.getListObject(new VariacaoNumberModel(), sql);

    }

    @Override
    public VariacaoNumberModel getRotacaoDiaAnterior() {
        return getRotacaoEspecificoDiasAtras(null);
    }

    @Override
    public VariacaoNumberModel getRotacaoEspecificoDiasAtras(Integer diasAtras) {
        String sql;

        if (Check.naoNulo(diasAtras)) {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from rotacao "
                +
                "where data::date = (now() - interval '" + diasAtras + " day')::date";
        } else {
            sql = "select round(max(valor),2) as max, round(avg(valor),2) as med, round(min(valor),2) as min, min(data) as data from rotacao "
                +
                "where data::date = (now() - interval '1 day')::date";
        }

        return query.getListObject(new VariacaoNumberModel(), sql).get(0);
    }

}
