package com.digitalhouse.dao.impl;

import com.digitalhouse.dao.ConfiguracaoJDBC;
import com.digitalhouse.dao.IDao;
import com.digitalhouse.model.Materia;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MateriaDaoH2 implements IDao<Materia> {

    final static Logger logger = Logger.getLogger(MateriaDaoH2.class);
    private ConfiguracaoJDBC configuracaoJDBC;
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/materia;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
        return configuracaoJDBC.getConnection();
    }

    @Override
    public Materia salvar(Materia materia) throws SQLException, ClassNotFoundException {
        String SQLInsert = String.format("INSERT INTO materia(nome) VALUES('%S')", materia.getNome());
        Connection connection = null;

        try {
            logger.info("Abrindo Conexao");
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQLInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                materia.setId(resultSet.getInt(1));
            }
        } catch (SQLException e){
            logger.error("Erro ao tentar conexão!");
            e.printStackTrace();
        }
        finally {
            logger.info("Fechando a Conexao");
            connection.close();
        }
        return materia;
    }

    @Override
    public List<Materia> buscarTodos() throws SQLException, ClassNotFoundException {
        String query = String.format("SELECT * FROM materia");
        Connection connection = null;

        List<Materia> materias = new ArrayList<>();
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                materias.add(new Materia(resultSet.getInt("id"),resultSet.getString("nome")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return materias;
    }
}
