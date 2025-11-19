package com.proaula.spring.synergy.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proaula.spring.synergy.Model.Participacion;
import com.proaula.spring.synergy.Model.ParticipacionId;
import com.proaula.spring.synergy.Repository.ParticipacionRepository;
import com.proaula.spring.synergy.Service.ParticipacionService;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Repository.ProyectoRepository;

@Service
public class ParticipacionServiceImpl implements ParticipacionService {

    @Autowired
    private ParticipacionRepository participacionRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Participacion inscribirUsuario(Long proyectoId, Long usuarioId) {

        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new RuntimeException("Proyecto no existe"));

        Usuarios usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        ParticipacionId id = new ParticipacionId(usuarioId, proyectoId);

        // evitar duplicados
        if (participacionRepository.existsById(id)) {
            return null;
        }

        // crear participación correctamente usando usuario y proyecto
        Participacion participacion = new Participacion(usuario, proyecto);

        return participacionRepository.save(participacion);
    }

    @Override
    public List<Participacion> listarParticipantes(Long proyectoId) {
        return participacionRepository.findByProyecto_Id(proyectoId);
    }

    @Override
    public boolean yaInscrito(Long proyectoId, Long usuarioId) {
        return participacionRepository.existsByUsuario_IdAndProyecto_Id(usuarioId, proyectoId);
    }

    @Override
    public void eliminarParticipacion(Long proyectoId, Long usuarioId) {

        participacionRepository
            .findByProyecto_IdAndUsuario_Id(proyectoId, usuarioId)
            .ifPresent(participacionRepository::delete);
    }
}
