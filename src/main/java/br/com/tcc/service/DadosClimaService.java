package br.com.tcc.service;

import java.text.ParseException;

import br.com.tcc.model.DadosClimaModel;

public interface DadosClimaService {

    /**
     * obtém os ultimos dados captados {altitude, velocidade, umidade, rotação,
     * ...}
     *
     * @return dadosClimaModel
     * @throws ParseException
     */
    DadosClimaModel getUltimosValores() throws ParseException;

    void insereDadosCaptados(String content);

}
