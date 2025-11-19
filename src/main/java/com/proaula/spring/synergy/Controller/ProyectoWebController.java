package com.proaula.spring.synergy.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Service.ProyectoService;
import com.proaula.spring.synergy.Service.ParticipacionService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoWebController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ParticipacionService participacionService;

    // ==============================
    //     REGISTRO DE PROYECTOS
    // ==============================
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoService.listar());
        return "registro_proyectos";
    }

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

        proyecto.setIdLider(usuarioId);

        proyectoService.guardar(proyecto, archivo);

        model.addAttribute("mensajeExito", "Proyecto registrado correctamente");
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoService.listar());

        return "registro_proyectos";
    }

    // ==============================
    //      LISTA DE PROYECTOS
    // ==============================
    @GetMapping("/lista")
    public String listarProyectos(HttpSession session, Model model) {

        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("proyectos", proyectoService.listar());

        return "Lista_Proyectos";
    }

    // ==============================
    //   VISTA PARA PARTICIPAR / SALIR
    // ==============================
    @GetMapping("/participar")
    public String mostrarListaParticipacion(HttpSession session, Model model) {

        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("proyectos", proyectoService.listar());

        return "Lista_Proyectos";
    }

    // ==============================
    //      INSCRIBIRSE AL PROYECTO
    // ==============================
    @PostMapping("/participacion/inscribir/{proyectoId}/{usuarioId}")
    public String inscribir(@PathVariable Long proyectoId,
                            @PathVariable Long usuarioId) {

        participacionService.inscribirUsuario(proyectoId, usuarioId);

        return "redirect:/proyectos/lista";
    }

    // ==============================
    //       SALIR DEL PROYECTO
    // ==============================
    @PostMapping("/participacion/eliminar/{proyectoId}/{usuarioId}")
    public String salirDelProyecto(@PathVariable Long proyectoId,
                                   @PathVariable Long usuarioId) {

        participacionService.eliminarParticipacion(proyectoId, usuarioId);

        return "redirect:/proyectos/lista";
    }

}
