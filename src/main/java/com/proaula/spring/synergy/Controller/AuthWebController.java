package com.proaula.spring.synergy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthWebController {

    // PÁGINA DE INICIO
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    // PÁGINA DE REGISTRO DE USUARIO
    @GetMapping("/Registrarse")
    public String registrarse() {
        return "Registro";
    }

    // PÁGINA DE LOGIN
    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    // PÁGINA ACERCA DE (información de la app)
    @GetMapping("/acerca")
    public String acerca() {
        return "Acerca";
    }

    // PÁGINA DE REGISTRO DE PROYECTO
    @GetMapping("/registrarproyecto")
    public String registrarProyecto() {
        return "registro_proyecto";
    }

    // PÁGINA DE LISTA DE PROYECTOS
    @GetMapping("/listaproyectos")
    public String listaProyectos() {
        return "Lista_Proyectos";
    }

    // PÁGINA DE JERARQUÍA DE PROYECTOS
    @GetMapping("/jerarquia")
    public String jerarquia() {
        return "jerarquia";
    }

}
