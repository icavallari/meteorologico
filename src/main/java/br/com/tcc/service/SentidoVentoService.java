package br.com.tcc.service;

import java.util.List;

import br.com.tcc.entidade.SentidoVento;
import br.com.tcc.model.VariacaoStringModel;

public interface SentidoVentoService {

    /**
     * obtem a moda, media e mediana do dia anterior
     *
     * @return
     */
    VariacaoStringModel getVentosDia();

    /**
     * obtem uma lista com moda, media e mediana de cada hora do dia anterior
     *
     * @return
     */
    List<VariacaoStringModel> getVentosHoraDiaAnterior();

    /**
     * obtem uma lista com moda, media e mediana de cada hora de um dia
     * especifico
     *
     * @param diasAtras
     * @return
     */
    List<VariacaoStringModel> getVentosHoraEspecificoDiasAtras(Integer diasAtras);

    /**
     * obtem a moda, media e mediana de um dia especifico
     *
     * @param diasAtras
     * @return
     */
    VariacaoStringModel getVentosDiaEspecificoDiaAtras(Integer diasAtras);

    /**
     * obtem uma lista da entidade SentidoVento de um dia especifico
     *
     * @param diasAtras
     * @return
     */
    List<SentidoVento> getVentosDia(Integer diasAtras);

    /**
     * obtém uma lista de valores da ultima hora
     * @return
     */
    List<VariacaoStringModel> getVentosUltimaHora();

}
