package com.proaula.spring.synergy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Service.ProyectoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/lider")
public class LiderController {

    @Autowired
    private ProyectoService proyectoService;

    // =======================================================
    // VALIDAR QUE EL USUARIO SEA LÍDER
    // =======================================================
    private Usuarios validarLider(HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

        if (usuario == null)
            return null;

        if (!Usuarios.Rol.Lider.equals(usuario.getRol()))
            return null;

        return usuario;
    }


    // =======================================================
    // DASHBOARD DEL LÍDER (LISTA SOLO SUS PROYECTOS)
    // =======================================================
    @GetMapping("/dashboard")
    public String dashboardLider(HttpSession session, Model model) {

        Usuarios lider = validarLider(session);
        if (lider == null)
            return "redirect:/login";

        List<Proyecto> proyectos = proyectoService.obtenerProyectosDeLider(lider.getId());

        model.addAttribute("usuario", lider);
        model.addAttribute("proyectos", proyectos);

        return "Lider_Dashboard";
    }


    // =======================================================
    // FORMULARIO PARA CREAR UN NUEVO PROYECTO
    // =======================================================
    @GetMapping("/proyecto/nuevo")
    public String formularioNuevoProyecto(HttpSession session, Model model) {

        Usuarios lider = validarLider(session);
        if (lider == null)
            return "redirect:/login";

        model.addAttribute("proyecto", new Proyecto());

        return "Lider_Crear_Proyecto";
    }


    // =======================================================
    // GUARDAR NUEVO PROYECTO
    // =======================================================
    @PostMapping("/proyecto/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto,
                                  HttpSession session) {

        Usuarios lider = validarLider(session);
        if (lider == null)
            return "redirect:/login";

        // FORZAR QUE EL PROYECTO PERTENECE AL LÍDER
        proyecto.setLider(lider);

        proyectoService.guardar(proyecto, null);

        return "redirect:/lider/dashboard";
    }


    // =======================================================
    // FORMULARIO EDITAR PROYECTO
    // =======================================================
    @GetMapping("/proyecto/{id}/editar")
    public String editarProyecto(@PathVariable Long id,
                                 HttpSession session,
                                 Model model) {

        Usuarios lider = validarLider(session);
        if (lider == null)
            return "redirect:/login";

        Proyecto proyecto = proyectoService.buscarPorId(id);

        if (proyecto == null)
            return "redirect:/lider/dashboard";

        // VALIDAR QUE EL PROYECTO ES DE ESTE LÍDER
        if (!proyecto.getLider().equals(lider))
            return "redirect:/lider/dashboard";

        model.addAttribute("proyecto", proyecto);

        return "Lider_Editar_Proyecto";
    }


    // =======================================================
    // ACTUALIZAR PROYECTO
    // =======================================================
    @PostMapping("/proyecto/{id}/actualizar")
    public String actualizarProyecto(@PathVariable Long id,
                                     @ModelAttribute Proyecto proyecto,
                                     HttpSession session) {

        Usuarios lider = validarLider(session);
        if (lider == null)
            return "redirect:/login";

        // GARANTIZAR QUE SE MANTIENE EL DUEÑO DEL PROYECTO
        proyecto.setId(id);
        proyecto.setLider(lider);

        proyectoService.guardar(proyecto, null);

        return "redirect:/lider/dashboard";
    }

}
