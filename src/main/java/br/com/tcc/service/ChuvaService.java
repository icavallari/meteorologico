package br.com.tcc.service;

import java.util.List;

import br.com.tcc.entidade.Chuva;
import br.com.tcc.model.ModalChuvaModel;
import br.com.tcc.model.VariacaoStringModel;

public interface ChuvaService {

    /**
     * obtem a moda, media e mediana de um dia
     *
     * @return
     */
    VariacaoStringModel getChuvasDiaAnterior();

    /**
     * obtem uma lista com moda, media e mediana de cada hora de um dia
     *
     * @return
     */
    List<VariacaoStringModel> getChuvasHorasDiaAnterior();

    /**
     * obtem uma lista com moda, media e mediana de cada hora de um dia
     * especifico
     *
     * @return
     */
    List<VariacaoStringModel> getChuvasHorasDiaAnteriorEspecificoDiaAtras(Integer diasAtras);

    /**
     * obtem a moda, media e mediana de um dia especifico
     *
     * @return
     */
    VariacaoStringModel getChuvasDiaEspecificoDiaAtras(Integer diasAtras);

    /**
     * obtem uma lista de chuva de um determinado dia
     *
     * @param diasAtras
     *            / pode ser nulo
     * @return
     */
    List<Chuva> getChuvasDia(Integer diasAtras);

    /**
     * obtem uma lista de valores da ultima hora
     *
     * @return
     */
    List<VariacaoStringModel> getUltimaHora();

    /**
     * Obtém os 30 dados captados contendo a chuva e a temperatura
     *
     * @param diasAtras
     * @return modalChuvaModel
     */
    List<ModalChuvaModel> getChuvaTemperaturaUltimasHoras(Integer diasAtras);

}
