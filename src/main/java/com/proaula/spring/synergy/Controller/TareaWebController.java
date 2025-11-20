package com.proaula.spring.synergy.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Service.ProyectoService;
import com.proaula.spring.synergy.Service.TareaService;
import com.proaula.spring.synergy.Service.UsuarioService;
import com.proaula.spring.synergy.Service.ParticipacionService;

@Controller
@RequestMapping("/tareas")
public class TareaWebController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ParticipacionService participacionService;


    // ============================================
    //              PÁGINA DEL LÍDER
    // ============================================
    @GetMapping("/asignar/{idProyecto}")
    public String asignarTarea(@PathVariable Long idProyecto, Model model) {

        Proyecto proyecto = proyectoService.buscarPorId(idProyecto);
        List<Usuarios> usuarios = participacionService.listarUsuariosPorProyecto(idProyecto);

        // Tareas ya existentes del proyecto
        List<Tarea> tareas = tareaService.obtenerTareasPorProyecto(idProyecto);

        // Evitar LazyInitializationException al mostrar usuarios en Thymeleaf
        for (Tarea t : tareas) {
            t.getUsuarios().size();
        }

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("tareas", tareas);

        return "Lider_Asignar_Tareas";
    }


    // ============================================
    //                 GUARDAR TAREA
    // ============================================
    @PostMapping("/guardar")
    public String guardarTarea(
            @ModelAttribute Tarea tarea,
            @RequestParam Long proyectoId,
            @RequestParam(value = "usuariosSeleccionados", required = false) List<Long> usuariosIds,
            @RequestParam("fechaEntrega") String fechaEntregaStr) {

        Proyecto proyecto = proyectoService.buscarPorId(proyectoId);
        tarea.setProyecto(proyecto);

        // Fecha de entrega
        tarea.setFechaEntrega(LocalDate.parse(fechaEntregaStr));

        // Estado por defecto
        tarea.setEstado("Pendiente");

        // Guardado con usuarios asignados
        tareaService.guardarTarea(tarea, usuariosIds);

        return "redirect:/tareas/asignar/" + proyectoId;
    }


    // ============================================
    //                     BUZÓN
    // ============================================
    @GetMapping("/buzon/{idUsuario}")
    public String buzon(@PathVariable Long idUsuario, Model model) {

        List<Tarea> tareas = tareaService.obtenerTareasPorUsuario(idUsuario);

        // Necesario si en el buzón se muestran los usuarios asignados
        for (Tarea t : tareas) {
            t.getUsuarios().size();
        }

        Usuarios usuario = usuarioService.buscarPorId(idUsuario).orElse(null);

        model.addAttribute("usuario", usuario);
        model.addAttribute("tareas", tareas);

        return "Usuarios_Buzon_Tareas";
    }


    // ============================================
    //            CAMBIO DE ESTADO
    // ============================================
    @PostMapping("/estado/{idTarea}")
    public String actualizarEstado(
            @PathVariable Long idTarea,
            @RequestParam String estado,
            @RequestParam Long idUsuario) {

        tareaService.cambiarEstado(idTarea, estado);
        return "redirect:/tareas/buzon/" + idUsuario;
    }

    
}
