package com.proaula.spring.synergy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proaula.spring.synergy.Model.Usuarios;

@Repository
public interface usuarioRepository extends JpaRepository<Usuarios, Long> {
}
