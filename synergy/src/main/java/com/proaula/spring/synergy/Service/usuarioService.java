package com.proaula.spring.synergy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.usuarioRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuarios guardarUsuario(@NonNull Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuarios> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void eliminarUsuario(@NonNull Long id) {
        usuarioRepository.deleteById(id);
    }

}
