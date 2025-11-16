package com.proaula.spring.synergy.Service;

import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }
    public List<Tarea> tareasPorProyecto(Long proyectoId) {
    return tareaRepository.findByProyectoId(proyectoId);
}
}
