package com.proaula.spring.synergy.Service.Impl;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Repository.UsuarioRepository;
import com.proaula.spring.synergy.Repository.ProyectoRepository;
import com.proaula.spring.synergy.Service.ProyectoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;  // <-- necesario

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto guardar(Proyecto proyecto, MultipartFile archivo) {
        try {
            if (archivo != null && !archivo.isEmpty()) {
                System.out.println("Archivo recibido: " + archivo.getOriginalFilename());
            }

            Long idLider = proyecto.getIdLider();

            // ================================
            // 1. Validar que el usuario exista
            // ================================
            Usuarios usuario = usuarioRepository.findById(idLider)
                    .orElseThrow(() -> new RuntimeException("Usuario líder no existe"));

            // =============================================
            // 2. Si el usuario NO es Líder, cambiar su rol
            // =============================================
            if (!usuario.getRol().equals(Usuarios.Rol.Lider)) {
                usuario.setRol(Usuarios.Rol.Lider);
                usuarioRepository.save(usuario);
                System.out.println("🔄 Usuario actualizado a rol LIDER");
            }

            // =======================================================
            // 3. Insertar automáticamente en tabla lider_proyecto
            // =======================================================
            jdbc.update(
                "INSERT IGNORE INTO lider_proyecto (id) VALUES (?)",
                idLider
            );

            System.out.println("✅ Líder registrado en tabla lider_proyecto");

            // =======================
            // 4. Guardar el proyecto
            // =======================
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
}
