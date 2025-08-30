package com.cibertec.Proyecto.Clinica.application;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import com.cibertec.Proyecto.Clinica.domain.model.Paciente;
import com.cibertec.Proyecto.Clinica.domain.repository.PacienteRepository;
import com.cibertec.Proyecto.Clinica.domain.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository repository;
    public PacienteServiceImpl(PacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Paciente> listar() {
        return repository.findAll();
    }

    @Override
    public Paciente obtener(Integer id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Paciente no Encontrado"));
    }

    @Override
    public Paciente agregar(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return repository.update(paciente);
    }

    @Override
    public void eliminar(Integer id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        repository.deleteById(id);
    }

    @Override
    public Page<Paciente> listarPaginado(int page, int size) {
        return repository.findAllPaginado(PageRequest.of(page, size));
    }
}
