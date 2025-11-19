package com.proaula.spring.synergy.Service;

import java.util.List;
import com.proaula.spring.synergy.Model.Participacion;

public interface ParticipacionService {

    Participacion inscribirUsuario(Long proyectoId, Long usuarioId);

    List<Participacion> listarParticipantes(Long proyectoId);

    boolean yaInscrito(Long proyectoId, Long usuarioId);

    void eliminarParticipacion(Long proyectoId, Long usuarioId);
}
