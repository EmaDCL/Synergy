package com.proaula.spring.synergy.Repository;

import com.proaula.spring.synergy.Model.Participacion;
import com.proaula.spring.synergy.Model.ParticipacionId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipacionRepository extends JpaRepository<Participacion, ParticipacionId> {

    // Verificar si ya está inscrito
    boolean existsByUsuario_IdAndProyecto_Id(Long usuarioId, Long proyectoId);

    // Listar todos los participantes de un proyecto
    List<Participacion> findByProyecto_Id(Long proyectoId);

    // Buscar participación específica
    Optional<Participacion> findByProyecto_IdAndUsuario_Id(Long proyectoId, Long usuarioId);

        List<Participacion> findByUsuario_Id(Long usuarioId);
}
