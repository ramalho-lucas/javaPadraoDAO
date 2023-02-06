package com.digitalhouse.farmacia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracaoJDBC {

    private String jdbcDriver;
    private String dbUrl;
    private String nomeUsuario;
    private String senha;

    public ConfiguracaoJDBC(String jdbcDriver, String dbUrl, String nomeUsuario, String senha) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

//    public ConfiguracaoJDBC() {
//        this.jdbcDriver = "org.h2.Driver";
//        this.dbUrl = "jdbc:h2:mem:materiais;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'dao-exe1-create.sql'";
//        this.nomeUsuario = "sa";
//        this.senha = "";
//    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);
        return DriverManager.getConnection(dbUrl,nomeUsuario,senha);
    }
}
