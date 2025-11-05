package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Usuarios.Administrador;

public interface AdministradorService {
    List<Administrador> listar();
    Administrador guardar(Administrador usuario);
    Administrador buscar(Long id);
    void eliminar(Long id);
}
