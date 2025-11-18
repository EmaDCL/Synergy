package com.proaula.spring.synergy.Model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDate fechaEntrega;
    private String estado; // Pendiente, En progreso, Completada

    // --- Relación con PROYECTO ---
    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    // --- Nuevo: Usuario asignado ---
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarioAsignado;

    public Tarea() {}

    public Tarea(String titulo, String descripcion, LocalDate fechaEntrega, String estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    // GETTERS Y SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public Usuarios getUsuarioAsignado() { return usuarioAsignado; }
    public void setUsuarioAsignado(Usuarios usuarioAsignado) { this.usuarioAsignado = usuarioAsignado; }
}
