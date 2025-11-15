package com.proaula.spring.synergy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.usuarioRepository;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuarios guardarUsuario(Usuarios usuario) {

        // Validar si ya existe un correo
        usuarioRepository.findByCorreo(usuario.getCorreo())
                .ifPresent(u -> {
                    throw new RuntimeException("El correo ya está registrado");
                });

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuarios> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

