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

public class CriarDadosMock {

    private static String password = "manager", username = "postgres";
    final static Logger   logger   = Logger.getLogger(CriarDadosMock.class);

    static {
        Log4jManager.setLevel(Level.WARN);
    }

    @Test
    public void init() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        inserir();
    }

    public void inserir() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meteorologico", username, password);
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("bd/insert_dados_mock.sql"));
            logger.warn(">>> Dados MOCK inseridos em meteorologico");
        } catch (SQLException e) {

            logger.warn(
                ">>> Não foi possível inserir os dados. (Talvez você não tenha criado o banco de dados ainda. \"Se você não criou o banco de dados rode o Arquivo CriarBancoLocal.java\")");

            e.printStackTrace();
        }

    }

}
