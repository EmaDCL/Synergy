package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioWebController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/registrarse")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registro";
    }

    @PostMapping("/registrarse")
    public String registrar(@ModelAttribute("usuario") Usuarios usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model){
        model.addAttribute("loginDTO", new Object());
        return "login";
    }
}
