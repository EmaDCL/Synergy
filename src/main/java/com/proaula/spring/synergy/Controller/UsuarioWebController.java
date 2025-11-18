package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Model.Usuarios.Rol;
import com.proaula.spring.synergy.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;
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
    public String registrar(@ModelAttribute("usuario") Usuarios usuario) {

        if (usuario.getRol() == null) {
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

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String correo,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {

        Usuarios usuario = usuarioService.buscarPorCorreo(correo).orElse(null);

        if (usuario == null || !usuario.getContrasena().equals(contrasena)) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }

        session.setAttribute("usuario", usuario);
        session.setAttribute("usuarioId", usuario.getId());

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        return "dashboard_Usuario";
    }
}
