package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Model.Tarea;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Model.Usuarios.Rol;
import com.proaula.spring.synergy.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proaula.spring.synergy.Service.TareaService;
import java.util.List;

@Controller
public class UsuarioWebController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
private TareaService tareaService;


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
    return "Registro";
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
        return "Login";
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
            return "Login";
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

    @GetMapping("/lider/asignar-tareas")
public String mostrarAsignacionTareas() {
    return "Lider_AsignarTareas";
}

@GetMapping("/tareas/buzon")
public String mostrarBuzonTareas(HttpSession session, Model model) {

    // Obtener usuario logueado desde la sesión
    Usuarios usuario = (Usuarios) session.getAttribute("usuario");

    if (usuario == null) {
        return "redirect:/login";
    }

    // Obtener las tareas asignadas al usuario
    List<Tarea> tareas = tareaService.obtenerTareasPorUsuario(usuario.getId());

    model.addAttribute("usuario", usuario);
    model.addAttribute("tareas", tareas);

    return "Buzon_Tareas_Usuario"; // nombre del archivo .html sin extensión
}


}
