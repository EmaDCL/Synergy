package com.proaula.spring.synergy.Service;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuarios guardarUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuarios> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuarios> buscarPorCorreo(String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuarios> listarLideres() {
        return usuarioRepository.findByRol(Usuarios.Rol.Lider);
    }
}
