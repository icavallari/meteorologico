package br.com.tcc.model;

import java.math.BigDecimal;
import java.util.Date;

public class TabelaAuxiliar {

    private BigDecimal minAlti;
    private BigDecimal medAlti;
    private BigDecimal maxAlti;

    private String     medianaChuva;
    private String     mediaChuva;
    private String     modaChuva;

    private Integer    qtdChuva;
    private Integer    qtdChuvaMedia;
    private Integer    qtdChuvaModa;

    private Integer    minPres;
    private Integer    medPres;
    private Integer    maxPres;

    private BigDecimal minRota;
    private BigDecimal medRota;
    private BigDecimal maxRota;

    private String     medianaVento;
    private String     mediaVento;
    private String     modaVento;

    private Integer    qtdVento;
    private Integer    qtdVentoMedia;
    private Integer    qtdVentoModa;

    private BigDecimal minTemp;
    private BigDecimal medTemp;
    private BigDecimal maxTemp;

    private BigDecimal minUmid;
    private BigDecimal medUmid;
    private BigDecimal maxUmid;

    private BigDecimal minVelo;
    private BigDecimal medVelo;
    private BigDecimal maxVelo;

    private Date       data;

    public BigDecimal getMinAlti() {
        return minAlti;
    }

    public void setMinAlti(BigDecimal minAlti) {
        this.minAlti = minAlti;
    }

    public BigDecimal getMedAlti() {
        return medAlti;
    }

    public void setMedAlti(BigDecimal medAlti) {
        this.medAlti = medAlti;
    }

    public BigDecimal getMaxAlti() {
        return maxAlti;
    }

    public void setMaxAlti(BigDecimal maxAlti) {
        this.maxAlti = maxAlti;
    }

    public String getMedianaChuva() {
        return medianaChuva;
    }

    public void setMedianaChuva(String medianaChuva) {
        this.medianaChuva = medianaChuva;
    }

    public String getMediaChuva() {
        return mediaChuva;
    }

    public void setMediaChuva(String mediaChuva) {
        this.mediaChuva = mediaChuva;
    }

    public String getModaChuva() {
        return modaChuva;
    }

    public void setModaChuva(String modaChuva) {
        this.modaChuva = modaChuva;
    }

    public Integer getQtdChuva() {
        return qtdChuva;
    }

    public void setQtdChuva(Integer qtdChuva) {
        this.qtdChuva = qtdChuva;
    }

    public Integer getQtdChuvaMedia() {
        return qtdChuvaMedia;
    }

    public void setQtdChuvaMedia(Integer qtdChuvaMedia) {
        this.qtdChuvaMedia = qtdChuvaMedia;
    }

    public Integer getQtdChuvaModa() {
        return qtdChuvaModa;
    }

    public void setQtdChuvaModa(Integer qtdChuvaModa) {
        this.qtdChuvaModa = qtdChuvaModa;
    }

    public Integer getMinPres() {
        return minPres;
    }

    public void setMinPres(Integer minPres) {
        this.minPres = minPres;
    }

    public Integer getMedPres() {
        return medPres;
    }

    public void setMedPres(Integer medPres) {
        this.medPres = medPres;
    }

    public Integer getMaxPres() {
        return maxPres;
    }

    public void setMaxPres(Integer maxPres) {
        this.maxPres = maxPres;
    }

    public BigDecimal getMinRota() {
        return minRota;
    }

    public void setMinRota(BigDecimal minRota) {
        this.minRota = minRota;
    }

    public BigDecimal getMedRota() {
        return medRota;
    }

    public void setMedRota(BigDecimal medRota) {
        this.medRota = medRota;
    }

    public BigDecimal getMaxRota() {
        return maxRota;
    }

    public void setMaxRota(BigDecimal maxRota) {
        this.maxRota = maxRota;
    }

    public String getMedianaVento() {
        return medianaVento;
    }

    public void setMedianaVento(String medianaVento) {
        this.medianaVento = medianaVento;
    }

    public String getMediaVento() {
        return mediaVento;
    }

    public void setMediaVento(String mediaVento) {
        this.mediaVento = mediaVento;
    }

    public String getModaVento() {
        return modaVento;
    }

    public void setModaVento(String modaVento) {
        this.modaVento = modaVento;
    }

    public Integer getQtdVento() {
        return qtdVento;
    }

    public void setQtdVento(Integer qtdVento) {
        this.qtdVento = qtdVento;
    }

    public Integer getQtdVentoMedia() {
        return qtdVentoMedia;
    }

    public void setQtdVentoMedia(Integer qtdVentoMedia) {
        this.qtdVentoMedia = qtdVentoMedia;
    }

    public Integer getQtdVentoModa() {
        return qtdVentoModa;
    }

    public void setQtdVentoModa(Integer qtdVentoModa) {
        this.qtdVentoModa = qtdVentoModa;
    }

    public BigDecimal getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(BigDecimal minTemp) {
        this.minTemp = minTemp;
    }

    public BigDecimal getMedTemp() {
        return medTemp;
    }

    public void setMedTemp(BigDecimal medTemp) {
        this.medTemp = medTemp;
    }

    public BigDecimal getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(BigDecimal maxTemp) {
        this.maxTemp = maxTemp;
    }

    public BigDecimal getMinUmid() {
        return minUmid;
    }

    public void setMinUmid(BigDecimal minUmid) {
        this.minUmid = minUmid;
    }

    public BigDecimal getMedUmid() {
        return medUmid;
    }

    public void setMedUmid(BigDecimal medUmid) {
        this.medUmid = medUmid;
    }

    public BigDecimal getMaxUmid() {
        return maxUmid;
    }

    public void setMaxUmid(BigDecimal maxUmid) {
        this.maxUmid = maxUmid;
    }

    public BigDecimal getMinVelo() {
        return minVelo;
    }

    public void setMinVelo(BigDecimal minVelo) {
        this.minVelo = minVelo;
    }

    public BigDecimal getMedVelo() {
        return medVelo;
    }

    public void setMedVelo(BigDecimal medVelo) {
        this.medVelo = medVelo;
    }

    public BigDecimal getMaxVelo() {
        return maxVelo;
    }

    public void setMaxVelo(BigDecimal maxVelo) {
        this.maxVelo = maxVelo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
