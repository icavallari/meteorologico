package br.com.tcc.entidade;

import java.math.BigDecimal;
import java.util.Date;

public class Temperatura {

    private BigDecimal valor;
    private Date       data;
    private Estacao    estacao;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
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
