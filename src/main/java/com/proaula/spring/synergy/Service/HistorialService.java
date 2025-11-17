package com.proaula.spring.synergy.Service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HistorialService {
    
    // Clase para representar una acción en el historial
    public static class AccionHistorial {
        private final String tipo; // "CREAR", "EDITAR", "ELIMINAR"
        private final String entidad; // "PROYECTO", "TAREA", "USUARIO"
        private final String descripcion;
        private final LocalDateTime fecha;
        private final String usuario;
        private Map<String, Object> datosAnteriores;
        
        public AccionHistorial(String tipo, String entidad, String descripcion, String usuario) {
            this.tipo = tipo;
            this.entidad = entidad;
            this.descripcion = descripcion;
            this.usuario = usuario;
            this.fecha = LocalDateTime.now();
            this.datosAnteriores = new HashMap<>();
        }
        
        public void agregarDatoAnterior(String clave, Object valor) {
            this.datosAnteriores.put(clave, valor);
        }
        
        public String getFechaFormateada() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return fecha.format(formatter);
        }
        
        public String getIcono() {
            return switch (tipo) {
                case "CREAR" -> "➕";
                case "EDITAR" -> "✏️";
                case "ELIMINAR" -> "🗑️";
                default -> "📌";
            };
        }
        
        // Getters
        public String getTipo() { return tipo; }
        public String getEntidad() { return entidad; }
        public String getDescripcion() { return descripcion; }
        public LocalDateTime getFecha() { return fecha; }
        public String getUsuario() { return usuario; }
        public Map<String, Object> getDatosAnteriores() { return datosAnteriores; }
    }
    
    // Pila principal de acciones
    private final Stack<AccionHistorial> pilaAcciones = new Stack<>();
    
    // Pila para rehacer
    private final Stack<AccionHistorial> pilaDeshacer = new Stack<>();
    
    // Registrar acción
    public void registrarAccion(String tipo, String entidad, String descripcion, String usuario) {
        AccionHistorial accion = new AccionHistorial(tipo, entidad, descripcion, usuario);
        pilaAcciones.push(accion);
        pilaDeshacer.clear(); // Limpiar historial de rehacer
        
        System.out.println(accion.getIcono() + " Acción registrada: " + descripcion);
    }
    
    // Registrar con datos anteriores (para deshacer)
    public void registrarAccionConDatos(String tipo, String entidad, String descripcion, 
                                       String usuario, Map<String, Object> datosAnteriores) {
        AccionHistorial accion = new AccionHistorial(tipo, entidad, descripcion, usuario);
        accion.datosAnteriores = datosAnteriores;
        pilaAcciones.push(accion);
        pilaDeshacer.clear();
        
        System.out.println(accion.getIcono() + " Acción con datos registrada: " + descripcion);
    }
    
    // Deshacer última acción
    public AccionHistorial deshacer() {
        if (!pilaAcciones.isEmpty()) {
            AccionHistorial accion = pilaAcciones.pop();
            pilaDeshacer.push(accion);
            System.out.println("↩️ Deshaciendo: " + accion.getDescripcion());
            return accion;
        }
        System.out.println("⚠️ No hay acciones para deshacer");
        return null;
    }
    
    // Rehacer última acción deshecha
    public AccionHistorial rehacer() {
        if (!pilaDeshacer.isEmpty()) {
            AccionHistorial accion = pilaDeshacer.pop();
            pilaAcciones.push(accion);
            System.out.println("↪️ Rehaciendo: " + accion.getDescripcion());
            return accion;
        }
        System.out.println("⚠️ No hay acciones para rehacer");
        return null;
    }
    
    // Ver última acción sin eliminarla
    public AccionHistorial verUltimaAccion() {
        return pilaAcciones.isEmpty() ? null : pilaAcciones.peek();
    }
    
    // Obtener historial completo (del más reciente al más antiguo)
    public List<AccionHistorial> obtenerHistorialCompleto() {
        List<AccionHistorial> historial = new ArrayList<>(pilaAcciones);
        Collections.reverse(historial);
        return historial;
    }
    
    // Obtener últimas N acciones
    public List<AccionHistorial> obtenerUltimasAcciones(int cantidad) {
        List<AccionHistorial> historial = obtenerHistorialCompleto();
        return historial.subList(0, Math.min(cantidad, historial.size()));
    }
    
    // Filtrar por tipo de acción
    public List<AccionHistorial> filtrarPorTipo(String tipo) {
        return pilaAcciones.stream()
                .filter(a -> a.getTipo().equals(tipo))
                .toList();
    }
    
    // Filtrar por entidad
    public List<AccionHistorial> filtrarPorEntidad(String entidad) {
        return pilaAcciones.stream()
                .filter(a -> a.getEntidad().equals(entidad))
                .toList();
    }
    
    // Limpiar historial
    public void limpiarHistorial() {
        pilaAcciones.clear();
        pilaDeshacer.clear();
        System.out.println("🗑️ Historial limpiado");
    }
    
    // Contar acciones
    public int contarAcciones() {
        return pilaAcciones.size();
    }
    
    // Hay acciones para deshacer
    public boolean hayAccionesParaDeshacer() {
        return !pilaAcciones.isEmpty();
    }
    
    // Hay acciones para rehacer
    public boolean hayAccionesParaRehacer() {
        return !pilaDeshacer.isEmpty();
    }
    
    // Mostrar historial en consola
    public void mostrarHistorial() {
        System.out.println("\n📚 HISTORIAL DE ACCIONES:");
        List<AccionHistorial> historial = obtenerHistorialCompleto();
        
        if (historial.isEmpty()) {
            System.out.println("   (vacío)");
            return;
        }
        
        for (int i = 0; i < historial.size(); i++) {
            AccionHistorial accion = historial.get(i);
            System.out.println((i + 1) + ". " + accion.getIcono() + " " + 
                             accion.getTipo() + " " + accion.getEntidad() + ": " +
                             accion.getDescripcion() + 
                             " [" + accion.getFechaFormateada() + "]");
        }
    }
}