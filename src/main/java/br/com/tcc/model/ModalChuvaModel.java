package br.com.tcc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe responsavel por armazenar dados para o Modal de Chuva que possui a
 * média da temperatura de toda uma hora junto a variavel de chuva que mais
 * esteve presente em uma hora
 *
 * @author Rodrigo
 *
 */
public class ModalChuvaModel {

    private Date   data;
    private Float  temperatura;
    private String chuva;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public String getDataString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM - HH");
        return sdf.format(data) + " hrs";
    }

}
