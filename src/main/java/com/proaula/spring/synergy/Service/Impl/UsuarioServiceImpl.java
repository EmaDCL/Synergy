package com.proaula.spring.synergy.Service.Impl;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios guardarUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuarios> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuarios> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuarios> listarLideres() {
        return usuarioRepository.findByRol(Usuarios.Rol.Lider);
    }
}
