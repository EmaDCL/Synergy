package com.proaula.spring.synergy.Service.Impl;

import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.TareaRepository;
import com.proaula.spring.synergy.Repository.ProyectoRepository;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Service.TareaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    @Transactional
    public Tarea crearTareaConUsuarios(Long proyectoId, List<Long> usuarioIds, String titulo, String descripcion, LocalDate fechaEntrega) {

        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new RuntimeException("Proyecto no existe"));

        // obtener usuarios válidos
        List<Usuarios> usuarios = usuarioRepository.findAllById(usuarioIds);

        if (usuarios.isEmpty()) {
            throw new RuntimeException("No hay usuarios válidos para asignar la tarea");
        }

        // crear tarea
        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setEstado("Pendiente");
        tarea.setFechaEntrega(fechaEntrega);
        tarea.setProyecto(proyecto);

        // asignar usuarios (Set)
        Set<Usuarios> usuariosSet = new HashSet<>(usuarios);
        tarea.setUsuarios(usuariosSet);

        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> listarPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyecto_Id(proyectoId);
    }

    @Override
    public List<Tarea> listarPorUsuario(Long usuarioId) {
        return tareaRepository.findByUsuarios_Id(usuarioId);
    }

    @Override
    public Tarea buscarPorId(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
