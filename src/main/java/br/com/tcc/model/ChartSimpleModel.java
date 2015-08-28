package br.com.tcc.model;

import java.util.Calendar;
import java.util.Date;

public class ChartSimpleModel {

    private Float minima;
    private Float media;
    private Float maxima;
    private Date  data;

    public Float getMinima() {
        return minima;
    }

    public void setMinima(Float minima) {
        this.minima = minima;
    }

    public Float getMedia() {
        return media;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    public Float getMaxima() {
        return maxima;
    }

    public void setMaxima(Float maxima) {
        this.maxima = maxima;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getDataLong() {
        return data.getTime();
    }

}
