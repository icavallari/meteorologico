package br.com.tcc.model;

import java.util.Date;

public class DadosClimaModel {

    private Float  pressao;
    private Float  altitude;
    private Float  rotacao;
    private Float  velocidade;
    private String sentidoVento;
    private Float  umidade;
    private Float  temperatura;
    private String chuva;

    private Date   dataCaptura;

    public Float getPressao() {
        return pressao;
    }

    public void setPressao(Float pressao) {
        this.pressao = pressao;
    }

    public Float getAltitude() {
        return altitude;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public Float getRotacao() {
        return rotacao;
    }

    public void setRotacao(Float rotacao) {
        this.rotacao = rotacao;
    }

    public Float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Float velocidade) {
        this.velocidade = velocidade;
    }

    public String getSentidoVento() {
        return sentidoVento;
    }

    public void setSentidoVento(String sentidoVento) {
        this.sentidoVento = sentidoVento;
    }

    public Float getUmidade() {
        return umidade;
    }

    public void setUmidade(Float umidade) {
        this.umidade = umidade;
    }

    public Float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    public String getChuva() {
        return chuva;
    }

    public void setChuva(String chuva) {
        this.chuva = chuva;
    }

    public Date getDataCaptura() {
        return dataCaptura;
    }

    public void setDataCaptura(Date dataCaptura) {
        this.dataCaptura = dataCaptura;
    }

}
