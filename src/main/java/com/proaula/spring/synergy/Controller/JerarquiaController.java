package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Service.JerarquiaProyectoService;
import com.proaula.spring.synergy.Service.JerarquiaProyectoService.NodoProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jerarquia")
public class JerarquiaController {
    
    @Autowired
    private JerarquiaProyectoService jerarquiaService;
    
    // Ver jerarquía completa
    @GetMapping
    public String verJerarquia(Model model) {
        List<String> jerarquia = jerarquiaService.obtenerJerarquiaCompleta();
        NodoProyecto raiz = jerarquiaService.getRaiz();
        
        model.addAttribute("jerarquia", jerarquia);
        model.addAttribute("raiz", raiz);
        
        // Visualizar en consola también
        jerarquiaService.visualizarArbol();
        
        return "jerarquia";
    }
    
    // Agregar proyecto a la jerarquía
    @PostMapping("/agregar-proyecto")
    public String agregarProyecto(@RequestParam String nombre, @RequestParam Long id) {
        jerarquiaService.agregarProyecto(id, nombre);
        return "redirect:/jerarquia";
    }
    
    // Agregar subproyecto
    @PostMapping("/agregar-subproyecto")
    public String agregarSubproyecto(@RequestParam Long proyectoId, 
                                    @RequestParam Long subproyectoId,
                                    @RequestParam String nombre) {
        jerarquiaService.agregarSubproyecto(proyectoId, subproyectoId, nombre);
        return "redirect:/jerarquia";
    }
    
    // Agregar tarea
    @PostMapping("/agregar-tarea")
    public String agregarTarea(@RequestParam Long proyectoId, 
                              @RequestParam Long tareaId,
                              @RequestParam String nombre) {
        jerarquiaService.agregarTarea(proyectoId, tareaId, nombre);
        return "redirect:/jerarquia";
    }
    
    // Ver ruta (breadcrumb) de un proyecto
    @GetMapping("/ruta/{id}")
    @ResponseBody
    public List<String> obtenerRuta(@PathVariable Long id) {
        return jerarquiaService.obtenerRuta(id);
    }
    
    // Contar tareas de un proyecto
    @GetMapping("/contar-tareas/{id}")
    @ResponseBody
    public int contarTareas(@PathVariable Long id) {
        return jerarquiaService.contarTareasTotales(id);
    }
}