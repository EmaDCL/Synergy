package com.proaula.spring.synergy.Service;

import com.proaula.spring.synergy.Model.Tarea;
import java.util.List;
import java.time.LocalDate;

public interface TareaService {

    Tarea guardar(Tarea tarea);

    // crear una tarea asociada a un proyecto y a varios usuarios
    Tarea crearTareaConUsuarios(Long proyectoId, List<Long> usuarioIds, String titulo, String descripcion, LocalDate fechaEntrega);

    List<Tarea> listarPorProyecto(Long proyectoId);

    List<Tarea> listarPorUsuario(Long usuarioId);

    Tarea buscarPorId(Long id);

    void eliminar(Long id);
}
