package br.com.tcc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tcc.entidade.DirecoesVento;
import br.com.tcc.util.Check;

public class VariacaoStringModel {

    private String  moda;
    private String  media;
    private String  mediana;

    private Integer qtd;
    private Integer qtdMedia;
    private Integer qtdModa;

    private Date    data;

    public String getModa() {
        return moda;
    }

    public void setModa(String moda) {
        this.moda = moda;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMediana() {
        return mediana;
    }

    public void setMediana(String mediana) {
        this.mediana = mediana;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Integer getQtdMedia() {
        return qtdMedia;
    }

    public void setQtdMedia(Integer qtdMedia) {
        this.qtdMedia = qtdMedia;
    }

    public Integer getQtdModa() {
        return qtdModa;
    }

    public void setQtdModa(Integer qtdModa) {
        this.qtdModa = qtdModa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    // apenas para sentido do vento
    public String getDirecaoVentoModa() {
        if (Check.naoNulo(this.moda)) {
            return DirecoesVento.valueOf(getModa().replace("-", "_")).getPosicao();
        }
        return null;
    }

    public String getDirecaoVentoMediana() {
        if (Check.naoNulo(this.mediana)) {
            return DirecoesVento.valueOf(getMediana().replace("-", "_")).getPosicao().replace("11", "9");
        }
        return null;
    }

    public String getDirecaoVentoMedia() {
        if (Check.naoNulo(this.media)) {
            return DirecoesVento.valueOf(getMedia().replace("-", "_")).getPosicao().replace("11", "7");
        }
        return null;
    }

    public String getDataString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM - HH");
        return sdf.format(data) + " hrs";
    }

}
