package com.proaula.spring.synergy.Service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JerarquiaProyectoService {
    
    // Clase Nodo del árbol
    public static class NodoProyecto {
        private final Long id;
        private final String nombre;
        private final String tipo; // "ORGANIZACION", "PROYECTO", "SUBPROYECTO", "TAREA"
        private NodoProyecto padre;
        private final List<NodoProyecto> hijos;
        private final int nivel;
        
        public NodoProyecto(Long id, String nombre, String tipo, int nivel) {
            this.id = id;
            this.nombre = nombre;
            this.tipo = tipo;
            this.nivel = nivel;
            this.hijos = new ArrayList<>();
        }
        
        public void agregarHijo(NodoProyecto hijo) {
            hijo.padre = this;
            this.hijos.add(hijo);
        }
        
        public void eliminarHijo(NodoProyecto hijo) {
            this.hijos.remove(hijo);
            hijo.padre = null;
        }
        
        // Getters
        public Long getId() { return id; }
        public String getNombre() { return nombre; }
        public String getTipo() { return tipo; }
        public NodoProyecto getPadre() { return padre; }
        public List<NodoProyecto> getHijos() { return hijos; }
        public int getNivel() { return nivel; }
    }
    
    private final NodoProyecto raiz;
    
    public JerarquiaProyectoService() {
        // Nodo raíz de la organización
        raiz = new NodoProyecto(0L, "Synergy - Organización", "ORGANIZACION", 0);
    }
    
    // Agregar proyecto principal
    public NodoProyecto agregarProyecto(Long id, String nombre) {
        NodoProyecto proyecto = new NodoProyecto(id, nombre, "PROYECTO", 1);
        raiz.agregarHijo(proyecto);
        System.out.println("🏢 Proyecto agregado: " + nombre);
        return proyecto;
    }
    
    // Agregar subproyecto a un proyecto existente
    public NodoProyecto agregarSubproyecto(Long proyectoId, Long subproyectoId, String nombre) {
        NodoProyecto proyecto = buscarNodo(raiz, proyectoId);
        if (proyecto != null) {
            NodoProyecto subproyecto = new NodoProyecto(subproyectoId, nombre, "SUBPROYECTO", proyecto.getNivel() + 1);
            proyecto.agregarHijo(subproyecto);
            System.out.println("📁 Subproyecto agregado: " + nombre);
            return subproyecto;
        }
        return null;
    }
    
    // Agregar tarea a un proyecto/subproyecto
    public void agregarTarea(Long proyectoId, Long tareaId, String nombre) {
        NodoProyecto proyecto = buscarNodo(raiz, proyectoId);
        if (proyecto != null) {
            NodoProyecto tarea = new NodoProyecto(tareaId, nombre, "TAREA", proyecto.getNivel() + 1);
            proyecto.agregarHijo(tarea);
            System.out.println("✅ Tarea agregada: " + nombre);
        }
    }
    
    // Buscar nodo por ID (búsqueda en profundidad - DFS)
    private NodoProyecto buscarNodo(NodoProyecto nodo, Long id) {
        if (nodo.getId().equals(id)) {
            return nodo;
        }
        
        for (NodoProyecto hijo : nodo.getHijos()) {
            NodoProyecto encontrado = buscarNodo(hijo, id);
            if (encontrado != null) {
                return encontrado;
            }
        }
        
        return null;
    }
    
    // Obtener jerarquía completa (recorrido preorden)
    public List<String> obtenerJerarquiaCompleta() {
        List<String> resultado = new ArrayList<>();
        recorrerPreorden(raiz, resultado);
        return resultado;
    }
    
    private void recorrerPreorden(NodoProyecto nodo, List<String> resultado) {
        String indentacion = "  ".repeat(nodo.getNivel());
        String icono = obtenerIcono(nodo.getTipo());
        
        resultado.add(indentacion + icono + " " + nodo.getNombre());
        
        for (NodoProyecto hijo : nodo.getHijos()) {
            recorrerPreorden(hijo, resultado);
        }
    }
    
    private String obtenerIcono(String tipo) {
        return switch (tipo) {
            case "ORGANIZACION" -> "🏢";
            case "PROYECTO" -> "📊";
            case "SUBPROYECTO" -> "📁";
            case "TAREA" -> "✅";
            default -> "📌";
        };
    }
    
    // Obtener ruta desde raíz hasta un nodo (breadcrumb)
    public List<String> obtenerRuta(Long proyectoId) {
        NodoProyecto nodo = buscarNodo(raiz, proyectoId);
        List<String> ruta = new ArrayList<>();
        
        while (nodo != null) {
            ruta.add(0, nodo.getNombre());
            nodo = nodo.getPadre();
        }
        
        return ruta;
    }
    
    // Contar tareas totales en un proyecto
    public int contarTareasTotales(Long proyectoId) {
        NodoProyecto nodo = buscarNodo(raiz, proyectoId);
        if (nodo == null) return 0;
        
        return contarTareasRecursivo(nodo);
    }
    
    private int contarTareasRecursivo(NodoProyecto nodo) {
        int total = nodo.getTipo().equals("TAREA") ? 1 : 0;
        
        for (NodoProyecto hijo : nodo.getHijos()) {
            total += contarTareasRecursivo(hijo);
        }
        
        return total;
    }
    
    // Visualizar árbol en formato de texto
    public void visualizarArbol() {
        System.out.println("\n🌳 JERARQUÍA DE PROYECTOS:");
        visualizarNodo(raiz, "", true);
    }
    
    private void visualizarNodo(NodoProyecto nodo, String prefijo, boolean esUltimo) {
        System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + 
                         obtenerIcono(nodo.getTipo()) + " " + nodo.getNombre());
        
        List<NodoProyecto> hijos = nodo.getHijos();
        for (int i = 0; i < hijos.size(); i++) {
            boolean ultimoHijo = (i == hijos.size() - 1);
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");
            visualizarNodo(hijos.get(i), nuevoPrefijo, ultimoHijo);
        }
    }
    
    // Obtener nodo raíz
    public NodoProyecto getRaiz() {
        return raiz;
    }
}