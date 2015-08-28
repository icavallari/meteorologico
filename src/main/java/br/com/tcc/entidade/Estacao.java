package br.com.tcc.entidade;

import java.awt.Point;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Estacao{

    private Integer id;
    private String  cidade;
    private Point   localizacao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date    dataCriacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Point getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Point localizacao) {
        this.localizacao = localizacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
