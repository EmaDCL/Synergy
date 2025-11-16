package com.proaula.spring.synergy.Service;


import java.time.LocalDateTime;

import com.proaula.spring.synergy.Model.Comentario;
import com.proaula.spring.synergy.Repository.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Repository.ProyectoRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Comentario> listarPorProyecto(Long proyectoId) {
        return comentarioRepository.findByProyectoId(proyectoId);
    }

    public void guardar(Long proyectoId, String texto, MultipartFile archivo) {

        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        Comentario c = new Comentario();
        c.setContenido(texto);
        c.setFecha(LocalDateTime.now());
        c.setProyecto(proyecto);

        comentarioRepository.save(c);
    }
    // Guardar
    public Comentario guardarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Listar todos
    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    // Buscar por ID
    public Comentario obtenerPorId(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    // Eliminar
    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
}

