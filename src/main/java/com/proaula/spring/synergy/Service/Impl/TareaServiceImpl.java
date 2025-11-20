package com.proaula.spring.synergy.Service.Impl;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.TareaRepository;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Service.TareaService;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuariosRepo;

    @Override
    public Tarea guardarTarea(Tarea tarea, List<Long> usuariosIds) {

        if (usuariosIds != null && !usuariosIds.isEmpty()) {

            Set<Usuarios> usuariosAsignados = usuariosIds.stream()
                    .map(id -> usuariosRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id)))
                    .collect(Collectors.toSet());

            tarea.setUsuarios(usuariosAsignados);

        } else {
            tarea.setUsuarios(new HashSet<>()); // <-- CORREGIDO
        }

        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> obtenerTareasPorUsuario(Long usuarioId) {
        return tareaRepository.findByUsuarios_Id(usuarioId);
    }

@Override
public List<Tarea> obtenerTareasPorProyecto(Long idProyecto) {
    return tareaRepository.findByProyectoIdConUsuarios(idProyecto);
}

    @Override
    public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    @Override
    public void cambiarEstado(Long tareaId, String nuevoEstado) {
        Tarea tarea = tareaRepository.findById(tareaId).orElse(null);

        if (tarea != null) {
            tarea.setEstado(nuevoEstado);
            tareaRepository.save(tarea);
        }
    }
        @Override
public void marcarComoCompletada(Long idTarea) {
    Tarea tarea = tareaRepository.findById(idTarea)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

    tarea.setEstado("Completada");
    tareaRepository.save(tarea);
}
    
}
