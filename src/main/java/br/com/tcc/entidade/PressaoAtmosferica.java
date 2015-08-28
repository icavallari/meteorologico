package br.com.tcc.entidade;

import java.util.Date;

public class PressaoAtmosferica {

    private Integer valor;
    private Date    data;
    private Estacao estacao;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

}
