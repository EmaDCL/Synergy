package com.proaula.spring.synergy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Service.ProyectoService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoWebController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoService.listar());
        return "proyectos"; // una vista HTML: proyectos.html
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyecto-form"; // proyecto-form.html
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardar(proyecto);
        return "redirect:/proyectos";
    }
}

