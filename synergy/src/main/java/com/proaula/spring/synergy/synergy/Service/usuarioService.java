package com.proaula.spring.synergy.synergy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proaula.spring.synergy.synergy.Model.Usuarios;
import com.proaula.spring.synergy.synergy.Repository.usuarioRepository;

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

public Usuarios buscarPorId(Long id) {
    return usuarioRepository.findById(id).orElse(null);
}

public void eliminarUsuario(Long id) {
    usuarioRepository.deleteById(id);
}
}

