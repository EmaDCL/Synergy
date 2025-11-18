package com.proaula.spring.synergy.Repository;

import com.proaula.spring.synergy.Model.Participacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParticipacionRepository extends JpaRepository<Participacion, Long> {

    List<Participacion> findByProyecto_Id(Long proyectoId);

    boolean existsByProyecto_IdAndUsuario_Id(Long proyectoId, Long usuarioId);
}
