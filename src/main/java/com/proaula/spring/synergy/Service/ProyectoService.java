package com.proaula.spring.synergy.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Repository.ProyectoRepository;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Optional<Proyecto> buscar(Long id) {
        return proyectoRepository.findById(id);
    }

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}