package br.com.tcc.service;

import java.util.List;

import br.com.tcc.model.ChartSimpleModel;
import br.com.tcc.model.ModalSimplesModel;
import br.com.tcc.model.VariacaoNumberModel;

public interface TemperaturaService {

    List<ChartSimpleModel> getTemperaturasTodosOsDias();

    /**
     * obtém a temperatura máxima, média e miníma do dia anterior
     *
     * @return
     */
    VariacaoNumberModel getTemperaturasDiaAnterior();

    /**
     * obtém uma lista com a temperatura máxima, média e miníma de cada hora do
     * dia anterior
     *
     * @return
     */
    List<VariacaoNumberModel> getTemperaturasHorasDiaAnterior();

    /**
     * obtem a max, med e min da temperatura de um especifico dia atras
     *
     * @param diasAtras
     * @return
     */
    VariacaoNumberModel getTemperaturasEspecificoDiaAnterior(Integer diasAtras);

    /**
     * obtem uma lista com max, med e min de cada hora de um especifico dia
     * atras
     *
     * @param diasAtras
     * @return
     */
    List<VariacaoNumberModel> getTemperaturasHoraEspecificoDiasAtras(Integer diasAtras);

    /**
     * obtém as ultimas quantidade de horas desejadas
     *
     * @param ultimasHoras
     * @return
     */
    List<ModalSimplesModel> getUltimasHoras(Integer ultimasHoras);

    List<VariacaoNumberModel> getUltimaHora();

}
