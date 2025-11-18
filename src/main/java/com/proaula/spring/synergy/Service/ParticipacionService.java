package com.proaula.spring.synergy.Service;

import com.proaula.spring.synergy.Model.Participacion;
import java.util.List;

public interface ParticipacionService {

    Participacion inscribirUsuario(Long proyectoId, Long usuarioId);

    List<Participacion> listarParticipantes(Long proyectoId);

    boolean yaInscrito(Long proyectoId, Long usuarioId);

    void eliminarParticipacion(Long id);
}
