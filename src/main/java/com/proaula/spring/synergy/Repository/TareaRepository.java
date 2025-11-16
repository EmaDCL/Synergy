package com.proaula.spring.synergy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proaula.spring.synergy.Model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Buscar tareas por estado (opcional)
    Tarea findByEstado(String estado);
}
