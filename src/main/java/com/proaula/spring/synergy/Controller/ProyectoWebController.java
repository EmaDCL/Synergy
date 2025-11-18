package com.proaula.spring.synergy.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Service.ProyectoService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoWebController {

    @Autowired
    private ProyectoService proyectoService;

    // Mostrar formulario
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoService.listar());
        return "registro_proyectos";
    }

    // Guardar proyecto
    @PostMapping("/guardar")
    public String guardarProyecto(
            @ModelAttribute Proyecto proyecto,
            @RequestParam("archivo") MultipartFile archivo,
            HttpSession session,
            Model model) {

        Long usuarioId = (Long) session.getAttribute("usuarioId");

        if (usuarioId == null) {
            return "redirect:/login";
        }

        // Asignar líder
        proyecto.setIdLider(usuarioId);

        proyectoService.guardar(proyecto, archivo);

        model.addAttribute("mensajeExito", "Proyecto registrado correctamente");
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoService.listar());

        return "registro_proyectos";
    }

    @GetMapping("/mis-proyectos")
    public String listarProyectos(Model model) {
        model.addAttribute("proyectos", proyectoService.listar());
        return "Lista_Proyectos";
    }
}
