package com.digitalhouse.farmacia.dao.impl;

import com.digitalhouse.farmacia.dao.ConfiguracaoJDBC;
import com.digitalhouse.farmacia.dao.IDao;
import com.digitalhouse.farmacia.model.Medicamento;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicamentoDaoH2 implements IDao<Medicamento> {

    final static Logger logger = Logger.getLogger(MedicamentoDaoH2.class);
    private ConfiguracaoJDBC configuracaoJDBC;

   private Connection getConnection() throws SQLException, ClassNotFoundException {
       configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/farmacia;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
       return configuracaoJDBC.getConnection();
   }

    @Override
    public Medicamento salvar(Medicamento medicamento) throws SQLException {
        String SQLInsert = String.format("INSERT INTO medicamento(nome, laboratorio,quantidade, preco) VALUES ('%s','%s','%s','%s')",
                medicamento.getNome(),medicamento.getLaboratorio(),medicamento.getQuantidade(),medicamento.getPreco());
        Connection connection = null;

        try {
            logger.info("Abrindo conexao");
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                medicamento.setId(resultSet.getInt("id"));
            }
        } catch (Exception e){
            logger.error("Erro no banco de dados");
            e.printStackTrace();
        } finally {
            logger.info("Fechando Conexao");
            connection.close();
        }
        return medicamento;
    }
}
