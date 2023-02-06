package test.com.digitalhouse.service;


import com.digitalhouse.dao.ConfiguracaoJDBC;
import com.digitalhouse.dao.impl.MateriaDaoH2;
import com.digitalhouse.model.Materia;
import com.digitalhouse.service.MateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MateriaServiceTest {

    MateriaService materiaService;
    @BeforeEach
    void doBefore(){
        materiaService = new MateriaService();
    }

    @Test
    public void salvarMateriaTest() throws SQLException, ClassNotFoundException {

        Materia materia = new Materia();
        materia.setNome("Backend");

        materiaService.salvar(materia);

        assertTrue(materia.getId() > 0);
    }


}