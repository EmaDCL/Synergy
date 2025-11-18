package com.proaula.spring.synergy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Repository.ProyectoRepository;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private JdbcTemplate jdbc;

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    public Proyecto guardar(Proyecto proyecto, MultipartFile archivo) {
        try {
            if (archivo != null && !archivo.isEmpty()) {
                System.out.println("Archivo recibido: " + archivo.getOriginalFilename());
            }

            // Registrar al usuario como líder automáticamente
            jdbc.update(
                "INSERT IGNORE INTO lider_proyecto (id) VALUES (?)",
                proyecto.getIdLider()
            );

            return proyectoRepository.save(proyecto);

        } catch (Exception e) {
            System.out.println("❌ Error guardando proyecto: " + e.getMessage());
            throw e;
        }
    }

    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}
