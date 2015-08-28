package br.com.tcc.service;

import java.util.List;

public interface Query {

    Long getCount(String sql);

    /**
     * @param sql
     */
    void execute(String sql);

    /**
     * Obtém uma lista de T
     *
     * @param t
     * @param sql
     * @return t
     */
    <T> List<T> getListObject(T t, String sql);

    /**
     * Obtém a string de um único campo
     *
     * @param sql
     * @return string
     */
    String getSingleValue(String sql);

    /**
     * apenas para teste
     *
     * @param sql
     */
    void getTest(String sql);

}
