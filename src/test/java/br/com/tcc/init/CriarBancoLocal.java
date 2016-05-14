package br.com.tcc.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import br.com.tcc.teste.Log4jManager;

/**
 * Cria uma base de dados com todas as tabelas necessárias.
 *
 * OBS: A tabela estação não possui campo de GEOLOCALIZACAO, assim não é
 * necessário possuir o PostGis instalado.
 *
 * ATENÇÃO: não deve ser rodado em PRODUÇÂO pois apagaria o banco de dados
 *
 * @author Rodrigo
 */
public class CriarBancoLocal {

    private static String password = "manager", username = "postgres";
    final static Logger   logger   = Logger.getLogger(CriarBancoLocal.class);

    static {
        Log4jManager.setLevel(Level.INFO);
    }

    @Test
    public void init() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        criarBanco();
        criarTabelas();
    }

    public void criarBanco() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("bd/drop_bd.sql"));
            logger.warn(">>> Banco meteorologico apagado");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("bd/create_bd.sql"));
            logger.warn(">>> Banco meteorologico criado");
        } catch (SQLException e) {
            logger.warn(">>> Não foi possível conectar com o Postgres");
            e.printStackTrace();
        }
    }

    public void criarTabelas() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meteorologico", username, password);

            ScriptUtils.executeSqlScript(connection, new ClassPathResource("bd/create_tables.sql"));
            logger.warn(">>> Tabelas em meteorologico");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("bd/create_index.sql"));
            logger.warn(">>> Indices e Sequences criadas em meteorologico");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("bd/insert_estacao.sql"));
            logger.warn(">>> Estação IFSP criada em meteorologico");

        } catch (SQLException e) {
            logger.warn(">>> Não foi possível conectar com a base de dados Meteorologico");
            e.printStackTrace();
        }
    }

}
