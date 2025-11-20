package com.proaula.spring.synergy.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Repository.ProyectoRepository;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto guardar(Proyecto proyecto, MultipartFile archivo) {
        try {

            // Validar archivo (si viene)
            if (archivo != null && !archivo.isEmpty()) {
                System.out.println("Archivo recibido: " + archivo.getOriginalFilename());
            }

            // Validar líder
            if (proyecto.getLider() == null) {
                throw new RuntimeException("El proyecto debe tener un líder asignado");
            }

            Usuarios lider = usuarioRepository.findById(proyecto.getId())
                    .orElseThrow(() -> new RuntimeException("El líder asignado no existe"));

            // Si deseas obligar que el usuario sea líder:
            if (!Usuarios.Rol.Lider.equals(lider.getRol())) {
                lider.setRol(Usuarios.Rol.Lider);
                usuarioRepository.save(lider);
                System.out.println("🔄 El usuario fue actualizado a rol LIDER");
            }

            // Guardar el proyecto
            return proyectoRepository.save(proyecto);

        } catch (DataAccessException e) {
            System.out.println("❌ Error guardando proyecto: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<Proyecto> obtenerProyectosDeLider(Long idLider) {
        return proyectoRepository.findByIdLider(idLider);
    }

    public List<Proyecto> listarProyectos() {
        return proyectoRepository.findAll();
    }

}
