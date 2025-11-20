package com.proaula.spring.synergy.Service;

import java.util.List;
import java.util.Optional;

import com.proaula.spring.synergy.Model.Usuarios;

public interface UsuarioService {

    List<Usuarios> listarUsuarios();

    Usuarios guardarUsuario(Usuarios usuario);

    Optional<Usuarios> buscarPorId(Long id);

    Optional<Usuarios> buscarPorCorreo(String correo);

    void eliminarUsuario(Long id);

    List<Usuarios> listarLideres();


}
