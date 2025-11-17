package com.proaula.spring.synergy.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Repository.ProyectoRepository;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @Autowired(required = false)
    private JerarquiaProyectoService jerarquiaService;
    
    @Autowired(required = false)
    private HistorialService historialService;


    // LISTAR TODOS LOS PROYECTOS
    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }


    // GUARDAR PROYECTO (CON O SIN ARCHIVO)
    public Proyecto guardar(Proyecto proyecto, MultipartFile archivo) {
        try {
            if (archivo != null && !archivo.isEmpty()) {
                System.out.println("Archivo recibido: " + archivo.getOriginalFilename());
            }
            
            // Guardar en base de datos
            Proyecto proyectoGuardado = proyectoRepository.save(proyecto);
            
            // ✅ Agregar al árbol de jerarquía (solo si existe)
            if (jerarquiaService != null) {
                try {
                    jerarquiaService.agregarProyecto(
                        proyectoGuardado.getId(), 
                        proyectoGuardado.getNombre()
                    );
                } catch (Exception e) {
                    System.out.println("⚠️ Error en jerarquía: " + e.getMessage());
                }
            }
            
            // ✅ Registrar en historial (solo si existe)
            if (historialService != null) {
                try {
                    historialService.registrarAccion(
                        "CREAR",
                        "PROYECTO",
                        "Proyecto creado: " + proyectoGuardado.getNombre(),
                        "Admin" // TODO: Obtener usuario actual
                    );
                } catch (Exception e) {
                    System.out.println("⚠️ Error en historial: " + e.getMessage());
                }
            }
            
            return proyectoGuardado;
            
        } catch (Exception e) {
            System.out.println("❌ Error guardando proyecto: " + e.getMessage());
            throw e;
        }
    }


    // ACTUALIZAR PROYECTO
    public Proyecto actualizar(Proyecto proyecto) {
        // Obtener datos anteriores
        Proyecto anterior = proyectoRepository.findById(proyecto.getId()).orElse(null);
        
        if (anterior != null && historialService != null) {
            try {
                // ✅ Guardar datos anteriores COMPLETOS para deshacer
                Map<String, Object> datosAnteriores = new HashMap<>();
                datosAnteriores.put("id", anterior.getId()); // ⚠️ Faltaba esto
                datosAnteriores.put("nombre", anterior.getNombre());
                datosAnteriores.put("descripcion", anterior.getDescripcion());
                // Agrega más campos según tu modelo Proyecto
                
                // ✅ Registrar en historial con datos anteriores
                historialService.registrarAccionConDatos(
                    "EDITAR",
                    "PROYECTO",
                    "Proyecto editado: " + proyecto.getNombre(),
                    "Admin", // TODO: Obtener usuario actual
                    datosAnteriores
                );
            } catch (Exception e) {
                System.out.println("⚠️ Error registrando en historial: " + e.getMessage());
            }
        }
        
        return proyectoRepository.save(proyecto);
    }


    // BUSCAR POR ID
    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }


    // ELIMINAR PROYECTO
    public void eliminar(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
        
        if (proyecto != null) {
            if (historialService != null) {
                try {
                    // ✅ Guardar datos COMPLETOS antes de eliminar
                    Map<String, Object> datosAnteriores = new HashMap<>();
                    datosAnteriores.put("id", proyecto.getId());
                    datosAnteriores.put("nombre", proyecto.getNombre());
                    datosAnteriores.put("descripcion", proyecto.getDescripcion());
                    // Agrega más campos según tu modelo Proyecto
                    
                    // ✅ Registrar en historial
                    historialService.registrarAccionConDatos(
                        "ELIMINAR",
                        "PROYECTO",
                        "Proyecto eliminado: " + proyecto.getNombre(),
                        "Admin", // TODO: Obtener usuario actual
                        datosAnteriores
                    );
                } catch (Exception e) {
                    System.out.println("⚠️ Error registrando eliminación: " + e.getMessage());
                }
            }
            
            proyectoRepository.deleteById(id);
        }
    }
}