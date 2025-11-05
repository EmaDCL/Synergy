package com.proaula.spring.synergy.synergy.Repository;

import com.proaula.spring.synergy.synergy.Model.Proyectos.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}