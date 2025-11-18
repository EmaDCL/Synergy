package com.proaula.spring.synergy.Service;

import com.proaula.spring.synergy.Model.LiderProyecto;

import java.util.List;

public interface LiderProyectoService {

    LiderProyecto asignarLider(Long usuarioId);

    LiderProyecto obtenerLider(Long id);

    List<LiderProyecto> listarLideres();
}
