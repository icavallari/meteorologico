package br.com.tcc.service;

import java.util.List;

import br.com.tcc.model.ChartSimpleModel;
import br.com.tcc.model.ModalSimplesModel;
import br.com.tcc.model.VariacaoNumberModel;

public interface UmidadeService {

    /**
     * obtem a max, med e min do dia anterior
     *
     * @return
     */
    VariacaoNumberModel getUmidadeDiaAnterior();

    /**
     * obtem uma lista com a max, med e min de cada hora do dia anterior
     *
     * @return
     */
    List<VariacaoNumberModel> getUmidadesHorasDiaAnterior();

    /**
     * obtem a max, med e min de um dia especifico
     *
     * @param diasAtras
     * @return
     */
    VariacaoNumberModel getUmidadeEspecificoDiasAtras(Integer diasAtras);

    /**
     * obtem uma lista com a max, med e min de cada hora de um dia especifico
     *
     * @param diasAtras
     * @return
     */
    List<VariacaoNumberModel> getUmidadesHoraEspecificoDiasAtras(Integer diasAtras);

    List<ModalSimplesModel> getUltimasHoras(Integer ultimasHoras);

    List<ChartSimpleModel> getUmidadesTodosOsDias();

    List<VariacaoNumberModel> getUltimaHora();

}
