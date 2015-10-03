package br.com.tcc.service;

import java.util.List;

import br.com.tcc.model.VariacaoNumberModel;

public interface VelocidadeVentoService {

    /**
     * obtem a max, med e min do dia anterior
     *
     * @return
     */
    VariacaoNumberModel getVelocidadesDiaAnterior();

    /**
     * obtem uma lista com max, med e min de cada hora do dia anterior
     *
     * @return
     */
    List<VariacaoNumberModel> getVelocidadesHorasDiaAnterior();

    /**
     * obtem a max, med e min de um especifico dia atras
     *
     * @param diasAtras
     * @return
     */
    VariacaoNumberModel getVelocidadesEspecificoDiaAtras(Integer diasAtras);

    /**
     * obtem uma lista com a max, med e min de cada hora de um especifico dia
     * atras
     *
     * @param diasAtras
     * @return
     */
    List<VariacaoNumberModel> getVelocidadesHoraEspecificoDiasAtras(Integer diasAtras);

    List<VariacaoNumberModel> getUltimaHora();

    List<VariacaoNumberModel> getUltimasHoras(Integer ultimasHoras);

}
