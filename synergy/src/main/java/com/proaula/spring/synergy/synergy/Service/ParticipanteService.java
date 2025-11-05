package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Usuarios.Participante;

public interface ParticipanteService {
    List<Participante> listar();
    Participante guardar(Participante participante);
    Participante buscar(Long id);
    void eliminar(Long id);
}
