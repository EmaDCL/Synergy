package com.proaula.spring.synergy.Service.Impl;

import com.proaula.spring.synergy.Model.EntregaTarea;
import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Repository.EntregaTareaRepository;
import com.proaula.spring.synergy.Repository.TareaRepository;
import com.proaula.spring.synergy.Service.EntregaTareaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EntregaTareaServiceImpl implements EntregaTareaService {

    @Autowired
    private EntregaTareaRepository entregaTareaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public void guardarEntrega(Long idTarea, MultipartFile archivo) throws IOException {

        Tarea tarea = tareaRepository.findById(idTarea)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        EntregaTarea entrega = new EntregaTarea();
        entrega.setTarea(tarea);
        entrega.setNombreArchivo(archivo.getOriginalFilename());
        entrega.setTipoArchivo(archivo.getContentType());
        entrega.setDatos(archivo.getBytes());

        entregaTareaRepository.save(entrega);
    }

    @Override
    public List<EntregaTarea> obtenerEntregasPorTarea(Long idTarea) {
        return entregaTareaRepository.findByTareaId(idTarea);
    }

    @Override
    public boolean tareaYaEntregada(Long idTarea) {
        return entregaTareaRepository.existsByTareaId(idTarea);
    }



}
