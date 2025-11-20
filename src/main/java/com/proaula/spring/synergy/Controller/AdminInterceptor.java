package com.proaula.spring.synergy.Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.proaula.spring.synergy.Model.Usuarios;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) throws Exception {
        
        String requestURI = request.getRequestURI();
        
        // Solo interceptar rutas /admin/
        if (!requestURI.startsWith("/admin/")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        
        // Si no hay sesión, redirigir a login
        if (session == null) {
            response.sendRedirect("/login");
            return false;
        }

        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        
        // Si no hay usuario en sesión, redirigir a login
        if (usuario == null) {
            response.sendRedirect("/login");
            return false;
        }

        // Validar que sea administrador
        if (!usuario.getRol().equals(Usuarios.Rol.Administrador)) {
            response.sendRedirect("/dashboard");
            return false;
        }

        // Todo bien, permitir acceso
        return true;
    }
}