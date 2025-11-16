package com.proaula.spring.synergy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Repository.ProyectoRepository;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;


    // LISTAR TODOS LOS PROYECTOS

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }


    // GUARDAR PROYECTO (CON O SIN ARCHIVO)

    public Proyecto guardar(Proyecto proyecto, MultipartFile archivo) {

        try {
            if (archivo != null && !archivo.isEmpty()) {
                // Si más adelante vas a guardar archivos, aquí colocas la lógica
                System.out.println("Archivo recibido: " + archivo.getOriginalFilename());
            }
        } catch (Exception e) {
            System.out.println("Error subiendo archivo: " + e.getMessage());
        }

        return proyectoRepository.save(proyecto);
    }


    // BUSCAR POR ID

    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }


    // ELIMINAR PROYECTO

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }

}
