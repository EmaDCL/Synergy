package com.proaula.spring.synergy.synergy.Repository;

import com.proaula.spring.synergy.synergy.Model.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}