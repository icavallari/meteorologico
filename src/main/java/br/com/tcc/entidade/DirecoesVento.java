package br.com.tcc.entidade;

public enum DirecoesVento {

    NORTE("N", "11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0"),
    NOR_NORDESTE("NNE", "0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0"),
    NORDESTE("NE", "0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0"),
    ES_NORDESTE("ENE", "0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0"),
    LESTE("L", "0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0"),
    ES_SUDESTE("ESE", "0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0"),
    SUDESTE("SE", "0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0 "),
    SU_SUDESTE("SSE", "0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0"),
    SUL("S", "0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0"),
    SU_SUDOESTE("SSO", "0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0"),
    SUDOESTE("SO", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0"),
    OES_SUDOESTE("OSO", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0"),
    OESTE("O", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0"),
    OES_NOROESTE("ONO", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0"),
    NOROESTE("NO", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0"),
    NOR_NOROESTE("NNO", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11");

    DirecoesVento(String sigla, String posicoes) {
        this.sigla = sigla;
        this.posicao = posicoes;
    }

    private final String sigla;
    private final String posicao;

    public String getSigla() {
        return sigla;
    }

    public String getPosicao() {
        return posicao;
    }

    public static String todas() {
        String v = "";
        for (DirecoesVento d : DirecoesVento.values()) {
            v += "'" + d.getSigla() + "',";
        }
        return v.substring(0, v.lastIndexOf(","));
    }
}