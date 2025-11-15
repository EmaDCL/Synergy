package com.proaula.spring.synergy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proaula.spring.synergy.Model.Usuarios;

@Repository
public interface usuarioRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByNombre(String nombre);
    Optional<Usuarios> findByCorreo(String correo);
}
