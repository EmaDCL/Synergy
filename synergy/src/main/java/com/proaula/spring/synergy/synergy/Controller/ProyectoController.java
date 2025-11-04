package com.proaula.spring.synergy.synergy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

    import org.springframework.ui.Model;
import com.proaula.spring.synergy.synergy.Model.Usuarios.Usuario;

@Controller
public class ProyectoController {

    @GetMapping("/")
    public String inicio() {
        return "index"; // sin .html, en minúscula
    }

    @GetMapping("/registrarse")
    public String registrarse() {
        return "registrarse"; // sin .html, en minúscula
    }

        @GetMapping("/login")
    public String login() {
        return "login"; // sin .html, en minúscula
    }

    
        @GetMapping("/registrarproyecto")
    public String registrarproyecto() {
        return "registrarproyecto"; // sin .html, en minúscula
    }

            @GetMapping("/listaproyectos")
    public String listaproyectos() {
        return "listaproyectos"; // sin .html, en minúscula
    }


}
