package com.proaula.spring.synergy.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proaula.spring.synergy.Model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Tareas asignadas a un usuario
    List<Tarea> findByUsuarios_Id(Long usuarioId);

    // Tareas de un proyecto específico
    List<Tarea> findByProyecto_Id(Long proyectoId);
}
