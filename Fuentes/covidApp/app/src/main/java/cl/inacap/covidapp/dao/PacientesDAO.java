package cl.inacap.covidapp.dao;

import java.util.List;

import cl.inacap.covidapp.dto.Paciente;

public interface PacientesDAO {
    List<Paciente> getAll();
    Paciente save(Paciente p);
}
