package com.proaula.spring.synergy.synergy.Repository;

import com.proaula.spring.synergy.synergy.Model.Usuarios.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}