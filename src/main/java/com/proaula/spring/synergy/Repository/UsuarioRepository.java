package com.proaula.spring.synergy.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proaula.spring.synergy.Model.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByCorreo(String correo);

    List<Usuarios> findByRol(Usuarios.Rol rol);  // ← IMPORTANTE
}
