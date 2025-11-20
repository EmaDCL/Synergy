package com.proaula.spring.synergy.Repository;

import com.proaula.spring.synergy.Model.Tarea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Listar tareas de un proyecto
    List<Tarea> findByProyecto_Id(Long proyectoId);

    // Listar tareas donde participa un usuario (ManyToMany)
    List<Tarea> findByUsuarios_Id(Long usuarioId);
}
