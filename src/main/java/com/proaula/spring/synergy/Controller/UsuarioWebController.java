package com.proaula.spring.synergy.Controller; // ← Minúscula

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Model.Usuarios.Rol;
import com.proaula.spring.synergy.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioWebController {
    
    @Autowired
    private UsuarioService usuarioService;
    

    @GetMapping("/")
    public String mostrarIndex() {
        return "index";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
   
    @GetMapping("/registrarse")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registro";
    }
    
    @PostMapping("/registrarse")
    public String registrar(@ModelAttribute("usuario") Usuarios usuario, 
                           @RequestParam(required = false) String rol) {
  
        if (rol != null && !rol.isEmpty()) {
            usuario.setRol(Rol.valueOf(rol));
        } else {
            usuario.setRol(Rol.Participante); 
        }
        
        usuarioService.guardarUsuario(usuario);
        return "redirect:/login";
    }
    

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("loginDTO", new Object());
        return "login";
    }
    

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        return "dashboard_Usuario";
    }
}