package com.proaula.spring.synergy.Repository;

import com.proaula.spring.synergy.Model.LiderProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiderProyectoRepository extends JpaRepository<LiderProyecto, Long> {

}
