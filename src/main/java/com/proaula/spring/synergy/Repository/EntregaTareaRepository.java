package com.proaula.spring.synergy.Repository;

import com.proaula.spring.synergy.Model.EntregaTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntregaTareaRepository extends JpaRepository<EntregaTarea, Long> {

    List<EntregaTarea> findByTareaId(Long tareaId);   // Obtener entregas de una tarea

    boolean existsByTareaId(Long tareaId);            // Para verificar si ya entregó
}
