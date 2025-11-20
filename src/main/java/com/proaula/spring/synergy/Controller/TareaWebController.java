package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Model.*;
import com.proaula.spring.synergy.Service.TareaService;
import com.proaula.spring.synergy.Service.ParticipacionService;
import com.proaula.spring.synergy.Service.ProyectoService;
import com.proaula.spring.synergy.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tareas")
public class TareaWebController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private ParticipacionService participacionService;

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private UsuarioService usuarioService;


    // ========== GET: página para asignar tareas ==========
    @GetMapping("/asignar")
    public String mostrarFormularioAsignar(@RequestParam Long proyectoId,
                                           HttpSession session,
                                           Model model) {

        Usuarios usuarioSession = (Usuarios) session.getAttribute("usuario");
        if (usuarioSession == null) return "redirect:/login";

        Proyecto proyecto = proyectoService.buscarPorId(proyectoId);
        if (proyecto == null) return "redirect:/proyectos/lista";

        // validar que sea el líder
        if (proyecto.getIdLider() == null || !proyecto.getIdLider().equals(usuarioSession.getId())) {
            return "error/403";
        }

        List<Participacion> participaciones = participacionService.listarParticipantes(proyectoId);

        model.addAttribute("proyecto", proyecto);
        model.addAttribute(
                "usuariosParticipantes",
                participaciones.stream().map(Participacion::getUsuario).toList()
        );

        model.addAttribute("tareas", tareaService.listarPorProyecto(proyectoId));

        return "Lider_AsignarTareas";  // ← vista correcta
    }



    // ========== POST: crear tarea ==========
    @PostMapping("/asignar/{proyectoId}")
    public String asignarTarea(@PathVariable Long proyectoId,
                               @RequestParam(required = false, name = "usuariosAsignados") List<Long> usuarioIds,
                               @RequestParam String nombre,
                               @RequestParam String descripcion,
                               @RequestParam String fechaLimite,
                               HttpSession session,
                               Model model) {

        Usuarios usuarioSession = (Usuarios) session.getAttribute("usuario");
        if (usuarioSession == null) return "redirect:/login";

        Proyecto proyecto = proyectoService.buscarPorId(proyectoId);
        if (proyecto == null) return "redirect:/proyectos/lista";

        // validar líder
        if (proyecto.getIdLider() == null || !proyecto.getIdLider().equals(usuarioSession.getId())) {
            return "error/403";
        }

        LocalDate fecha = LocalDate.parse(fechaLimite);

        // VALIDACIÓN → si no seleccionó usuarios
        if (usuarioIds == null || usuarioIds.isEmpty()) {

            model.addAttribute("error", "Debe seleccionar al menos un usuario");

            // recargar datos del GET original
            model.addAttribute("proyecto", proyecto);
            model.addAttribute(
                    "usuariosParticipantes",
                    participacionService.listarParticipantes(proyectoId)
                            .stream()
                            .map(Participacion::getUsuario)
                            .collect(Collectors.toList())
            );
            model.addAttribute("tareas", tareaService.listarPorProyecto(proyectoId));

            return "Lider_AsignarTareas";  // vista correcta
        }

        // crear tarea
        tareaService.crearTareaConUsuarios(proyectoId, usuarioIds, nombre, descripcion, fecha);

        return "redirect:/tareas/asignar?proyectoId=" + proyectoId;
    }



    // ========== LISTAR tareas por proyecto ==========
    @GetMapping("/proyecto/{proyectoId}")
    public String listarPorProyecto(@PathVariable Long proyectoId, HttpSession session, Model model) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        Proyecto proyecto = proyectoService.buscarPorId(proyectoId);
        if (proyecto == null) return "redirect:/proyectos/lista";

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("tareas", tareaService.listarPorProyecto(proyectoId));
        return "tareas_proyecto";
    }



    // ========== LISTAR tareas del usuario ==========
    @GetMapping("/mis-tareas")
    public String listarMisTareas(HttpSession session, Model model) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        List<Tarea> tareas = tareaService.listarPorUsuario(usuario.getId());
        model.addAttribute("tareas", tareas);
        model.addAttribute("usuario", usuario);
        return "tareas_usuario";
    }



    // ========== ELIMINAR tarea ==========
    @PostMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        Tarea tarea = tareaService.buscarPorId(id);
        if (tarea == null) return "redirect:/proyectos/lista";

        Long proyectoId = tarea.getProyecto() != null ? tarea.getProyecto().getId() : null;

        tareaService.eliminar(id);

        if (proyectoId != null)
            return "redirect:/tareas/asignar?proyectoId=" + proyectoId;

        return "redirect:/tareas/mis-tareas";
    }
}
