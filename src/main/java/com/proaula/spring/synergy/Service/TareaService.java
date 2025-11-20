package com.proaula.spring.synergy.Service;

import java.util.List;
import com.proaula.spring.synergy.Model.Tarea;

public interface TareaService {

    Tarea guardarTarea(Tarea tarea, List<Long> usuariosIds);

    List<Tarea> obtenerTareasPorUsuario(Long usuarioId);

    List<Tarea> obtenerTareasPorProyecto(Long proyectoId);

    Tarea obtenerTareaPorId(Long id);

    void cambiarEstado(Long tareaId, String nuevoEstado);

    void marcarComoCompletada(Long idTarea);

}
