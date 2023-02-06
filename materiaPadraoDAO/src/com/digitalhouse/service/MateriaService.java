package com.digitalhouse.service;

import com.digitalhouse.dao.IDao;
import com.digitalhouse.dao.impl.MateriaDaoH2;
import com.digitalhouse.model.Materia;

import java.sql.SQLException;
import java.util.List;

public class MateriaService {
    private IDao<Materia> materiaIDao;

    public MateriaService() {
        this.materiaIDao = materiaIDao;
    }

    public Materia salvar(Materia materia) throws SQLException, ClassNotFoundException {
        IDao<Materia> materiaIDao = new MateriaDaoH2();
        return materiaIDao.salvar(materia);
    }

    public List<Materia> buscarTodos() throws SQLException, ClassNotFoundException {
        IDao<Materia> materiaIDao = new MateriaDaoH2();
        return materiaIDao.buscarTodos();

    }

}
