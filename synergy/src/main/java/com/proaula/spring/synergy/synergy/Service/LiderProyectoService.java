package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Usuarios.LiderProyecto;

public interface LiderProyectoService {
    List<LiderProyecto> listar();
    LiderProyecto guardar(LiderProyecto liderproyecto);
    LiderProyecto buscar(Long id);
    void eliminar(Long id);
}
