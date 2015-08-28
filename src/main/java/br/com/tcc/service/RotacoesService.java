package br.com.tcc.service;

import java.util.List;

import br.com.tcc.model.VariacaoNumberModel;

public interface RotacoesService {

    /**
     * obtem a max, med e min do dia anterior
     *
     * @return
     */
    VariacaoNumberModel getRotacaoDiaAnterior();

    /**
     * obtem uma lista com max, med e min de cada hora do dia anterior
     *
     * @return
     */
    List<VariacaoNumberModel> getRotacoesHorasDiaAnterior();

    /**
     * obtem a max, med e min do dia de um especifico dia
     *
     * @param diasAtras
     * @return
     */
    VariacaoNumberModel getRotacaoEspecificoDiasAtras(Integer diasAtras);

    /**
     * obtem uma lista da max, med e min de cada hora de um especifico dia atras
     *
     * @param diasAtras
     * @return
     */
    List<VariacaoNumberModel> getRotacoesHoraEspecificoDiasAtras(Integer diasAtras);

    List<VariacaoNumberModel> getUltimaHora();

}
