package com.proaula.spring.synergy.synergy.Repository;

import com.proaula.spring.synergy.synergy.Model.Usuarios.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}