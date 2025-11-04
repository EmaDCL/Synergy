package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Proyectos.Proyecto;

public interface ProyectoService {
    List<Proyecto> listar();
    Proyecto guardar(Proyecto proyecto);
    Proyecto buscar(Long id);
    void eliminar(Long id);
}
