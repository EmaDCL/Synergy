package com.proaula.spring.synergy.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proaula.spring.synergy.Model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
List<Comentario> findByProyectoId(Long proyectoId);

}
