package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Service.EntregaTareaService;
import com.proaula.spring.synergy.Service.TareaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tareas")
public class EntregaTareaController {

    @Autowired
    private EntregaTareaService entregaTareaService;

    @Autowired
    private TareaService tareaService;

    /**
     * 1. Mostrar formulario de entrega
     */
    @GetMapping("/entregar/{id}")
    public String mostrarFormularioEntrega(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        // Verificar sesión
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        // Obtener la tarea
        Tarea tarea = tareaService.obtenerTareaPorId(id);

        if (tarea == null) {
            model.addAttribute("error", "La tarea no existe.");
            return "redirect:/tareas/buzon";
        }

        model.addAttribute("tarea", tarea);
        return "Formulario_Entrega_Tarea";
    }

    /**
     * 2. Procesar entrega
     */
    @PostMapping("/entregar/{id}")
    public String procesarEntrega(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo,
            Model model) {

        Tarea tarea = tareaService.obtenerTareaPorId(id);
        model.addAttribute("tarea", tarea); // <-- NECESARIO PARA VOLVER AL FORM EN CASO DE ERROR

        if (tarea == null) {
            model.addAttribute("error", "La tarea no existe.");
            return "Formulario_Entrega_Tarea";
        }

        try {
            entregaTareaService.guardarEntrega(id, archivo);
            tareaService.marcarComoCompletada(id);

        } catch (Exception e) {
            model.addAttribute("error", "Error al enviar el archivo: " + e.getMessage());
            return "Formulario_Entrega_Tarea";
        }

        return "redirect:/tareas/buzon?entregaExitosa=true";
    }
}
