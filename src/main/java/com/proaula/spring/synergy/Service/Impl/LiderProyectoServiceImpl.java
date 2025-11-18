package com.proaula.spring.synergy.Service.Impl;

import com.proaula.spring.synergy.Model.LiderProyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.LiderProyectoRepository;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Service.LiderProyectoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiderProyectoServiceImpl implements LiderProyectoService {

    @Autowired
    private LiderProyectoRepository liderProyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public LiderProyecto asignarLider(Long usuarioId) {
        Usuarios usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear registro en tabla lider_proyecto
        LiderProyecto lider = new LiderProyecto();
        lider.setUsuario(usuario);

        return liderProyectoRepository.save(lider);
    }

    @Override
    public LiderProyecto obtenerLider(Long id) {
        return liderProyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Líder no encontrado"));
    }

    @Override
    public List<LiderProyecto> listarLideres() {
        return liderProyectoRepository.findAll();
    }
}
