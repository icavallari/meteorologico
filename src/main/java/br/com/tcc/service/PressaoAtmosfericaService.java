package br.com.tcc.service;

import java.util.List;

import br.com.tcc.model.ChartSimpleModel;
import br.com.tcc.model.ModalSimplesModel;
import br.com.tcc.model.VariacaoNumberModel;

public interface PressaoAtmosfericaService {

    /**
     * obtem a moda, media e mediana de um dia
     *
     * @return
     */
    VariacaoNumberModel getPressoesDiaAnterior();

    /**
     * obtem uma lista com moda, media e mediana de cada hora de um dia
     *
     * @return
     */
    List<VariacaoNumberModel> getPressoesHorasDiaAnterior();

    /**
     * obtem uma lista com moda, media e mediana de cada hora de um dia
     * especifico
     *
     * @return
     */
    List<VariacaoNumberModel> getPressoesHoraEspecificoDiasAtras(Integer diasAtras);

    /**
     * obtem a moda, media e mediana de um dia especifico
     *
     * @return
     */
    VariacaoNumberModel getPressoesEspecificoDiaAnterior(Integer diasAtras);

    List<ModalSimplesModel> getUltimasHoras(Integer ultimasHoras);

    List<ChartSimpleModel> getPressoesTodosOsDias();

    List<VariacaoNumberModel> getUltimaHora();

}
