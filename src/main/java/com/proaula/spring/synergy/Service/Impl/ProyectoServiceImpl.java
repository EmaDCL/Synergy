package com.proaula.spring.synergy.Service.Impl;

import com.proaula.spring.synergy.Model.Proyecto;
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

            // Registrar líder en tabla auxiliar (si no existiera)
            jdbc.update(
                "INSERT IGNORE INTO lider_proyecto (id) VALUES (?)",
                proyecto.getIdLider()
            );

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
