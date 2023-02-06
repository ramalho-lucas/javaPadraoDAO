package com.digitalhouse.farmacia.service;

import com.digitalhouse.farmacia.dao.IDao;
import com.digitalhouse.farmacia.dao.impl.MedicamentoDaoH2;
import com.digitalhouse.farmacia.model.Medicamento;

import java.sql.SQLException;

public class MedicamentoService {
    public Medicamento salvar(Medicamento medicamento) throws SQLException {
        IDao<Medicamento> medicamentoIDao = new MedicamentoDaoH2();
        return medicamentoIDao.salvar(medicamento);
    }
}
