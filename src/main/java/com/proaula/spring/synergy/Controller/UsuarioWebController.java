package com.proaula.spring.synergy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Service.usuarioService;

@Controller
public class UsuarioWebController {

    @Autowired
    private usuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registro"; 
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuarios usuario, Model model) {

        try {
            usuarioService.guardarUsuario(usuario);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}
