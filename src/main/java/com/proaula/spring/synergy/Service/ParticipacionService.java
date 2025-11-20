package com.proaula.spring.synergy.Service;

import java.util.List;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;

public interface ParticipacionService {

    boolean inscribirUsuario(Long proyectoId, Long usuarioId);

    List<com.proaula.spring.synergy.Model.Participacion> listarParticipantes(Long proyectoId);

    boolean yaInscrito(Long proyectoId, Long usuarioId);

    void eliminarParticipacion(Long proyectoId, Long usuarioId);

    List<Proyecto> listarProyectosPorUsuario(Long usuarioId);

    List<Usuarios> listarUsuariosPorProyecto(Long proyectoId);

}
