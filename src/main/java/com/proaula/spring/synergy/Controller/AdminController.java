package com.proaula.spring.synergy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proaula.spring.synergy.Model.Proyecto;
import com.proaula.spring.synergy.Model.Usuarios;
import com.proaula.spring.synergy.Service.ProyectoService;
import com.proaula.spring.synergy.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProyectoService proyectoService;

    // ============ DASHBOARD ADMIN ============
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        
        // Validar que sea administrador
        if (usuario == null || !usuario.getRol().equals(Usuarios.Rol.Administrador)) {
            return "redirect:/login";
        }

        // Cargar estadísticas
        List<Usuarios> usuarios = usuarioService.listarUsuarios();
        List<Proyecto> proyectos = proyectoService.listar();
        List<Usuarios> lideres = usuarioService.listarLideres();

        model.addAttribute("totalUsuarios", usuarios.size());
        model.addAttribute("totalProyectos", proyectos.size());
        model.addAttribute("totalLideres", lideres.size());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("admin", usuario);

        return "Admin_Dashboard";
    }

    // ============ ELIMINAR USUARIO ============
    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        Usuarios admin = (Usuarios) session.getAttribute("usuario");
        
        if (admin == null || !admin.getRol().equals(Usuarios.Rol.Administrador)) {
            return "redirect:/login";
        }

        // No puede eliminarse a sí mismo
        if (admin.getId().equals(id)) {
            model.addAttribute("error", "No puedes eliminar tu propia cuenta");
            return "redirect:/admin/dashboard?error=self";
        }

        // Validar que el usuario existe
        Usuarios usuarioAEliminar = usuarioService.buscarPorId(id).orElse(null);
        if (usuarioAEliminar == null) {
            return "redirect:/admin/dashboard?error=notfound";
        }

        try {
            usuarioService.eliminarUsuario(id);
            return "redirect:/admin/dashboard?success=usuario";
        } catch (Exception e) {
            return "redirect:/admin/dashboard?error=delete";
        }
    }

    // ============ ELIMINAR PROYECTO ============
    @PostMapping("/proyectos/eliminar/{id}")
    public String eliminarProyecto(
            @PathVariable Long id,
            HttpSession session) {

        Usuarios admin = (Usuarios) session.getAttribute("usuario");
        
        if (admin == null || !admin.getRol().equals(Usuarios.Rol.Administrador)) {
            return "redirect:/login";
        }

        // Validar que el proyecto existe
        Proyecto proyecto = proyectoService.buscarPorId(id);
        if (proyecto == null) {
            return "redirect:/admin/dashboard?error=notfound";
        }

        try {
            proyectoService.eliminar(id);
            return "redirect:/admin/dashboard?success=proyecto";
        } catch (Exception e) {
            return "redirect:/admin/dashboard?error=delete";
        }
    }

    // ============ VER DETALLES DE USUARIO ============
    @GetMapping("/usuarios/{id}")
    public String verUsuario(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        Usuarios admin = (Usuarios) session.getAttribute("usuario");
        
        if (admin == null || !admin.getRol().equals(Usuarios.Rol.Administrador)) {
            return "redirect:/login";
        }

        Usuarios usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("admin", admin);

        return "Admin_DetalleUsuario";
    }

    // ============ VER DETALLES DE PROYECTO ============
    @GetMapping("/proyectos/{id}")
    public String verProyecto(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        Usuarios admin = (Usuarios) session.getAttribute("usuario");
        
        if (admin == null || !admin.getRol().equals(Usuarios.Rol.Administrador)) {
            return "redirect:/login";
        }

        Proyecto proyecto = proyectoService.buscarPorId(id);
        if (proyecto == null) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("admin", admin);

        return "Admin_DetalleProyecto";
    }
}