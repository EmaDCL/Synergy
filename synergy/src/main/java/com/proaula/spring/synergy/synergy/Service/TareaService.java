package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Proyectos.Tarea;

public interface TareaService {
    List<Tarea> listar();
    Tarea guardar(Tarea tarea);
    Tarea buscar(Long id);
    void eliminar(Long id);
}
