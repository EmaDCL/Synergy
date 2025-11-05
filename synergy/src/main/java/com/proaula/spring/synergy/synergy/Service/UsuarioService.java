package com.proaula.spring.synergy.synergy.Service;
import java.util.List;
import com.proaula.spring.synergy.synergy.Model.Usuarios.Usuario;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario guardar(Usuario usuario);
    Usuario buscar(Long id);
    void eliminar(Long id);
}
