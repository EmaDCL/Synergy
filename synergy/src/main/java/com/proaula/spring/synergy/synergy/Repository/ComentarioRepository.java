package com.proaula.spring.synergy.synergy.Repository;

import com.proaula.spring.synergy.synergy.Model.Proyectos.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}