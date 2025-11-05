package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Proyectos.Comentario;

public interface ComentarioService {
    List<Comentario> listar();
    Comentario guardar(Comentario comentario);
    Comentario buscar(Long id);
    void eliminar(Long id);
}
