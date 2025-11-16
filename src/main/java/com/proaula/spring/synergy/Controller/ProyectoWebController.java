package com.proaula.spring.synergy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Service.ComentarioService;
import com.proaula.spring.synergy.Service.ProyectoService;
import com.proaula.spring.synergy.Service.TareaService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoWebController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private TareaService tareaService;

    // REGISTRAR PROYECTO 

    @GetMapping("/registrar")
    public String mostrarRegistro(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoService.listar());
        return "RegistroProyectos";
    }

    // GUARDAR PROYECTO 

    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto,
            @RequestParam("archivo") MultipartFile archivo,
            Model model) { 
            proyectoService.guardar(proyecto, archivo);
        model.addAttribute("mensajeExito", "Proyecto registrado correctamente");
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoService.listar());
        return "RegistroProyectos";
    }

    // LISTAR PROYECTOS 

    @GetMapping("/mis-proyectos")
    public String listarProyectos(Model model) {
        model.addAttribute("proyectos", proyectoService.listar());
        return "Lista_Proyectos";
    }

    // VER COMENTARIOS + TAREAS DEL PROYECTO

    @GetMapping("/{id}/comentarios")
    public String verComentarios(@PathVariable Long id, Model model) {
        Proyecto p = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", p);
        model.addAttribute("tareas", tareaService.tareasPorProyecto(id));
        model.addAttribute("comentarios", comentarioService.listarPorProyecto(id));
        return "Comentarios_Proyectos";
    }

    // ENVIAR COMENTARIO (POST)

    @PostMapping("/enviar-comentario")
    public String enviarComentario(
            @RequestParam Long proyectoId,
            @RequestParam String comentario,
            @RequestParam(required = false) MultipartFile archivo) {
        comentarioService.guardar(proyectoId, comentario, archivo);
        return "redirect:/proyectos/" + proyectoId + "/comentarios";
    }
}
