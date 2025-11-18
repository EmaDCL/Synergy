package com.proaula.spring.synergy.Model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participacion")
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con proyectos
    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    // Relación con usuarios
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion = LocalDateTime.now();

    public Participacion() {}

    public Participacion(Proyecto proyecto, Usuarios usuario) {
        this.proyecto = proyecto;
        this.usuario = usuario;
        this.fechaInscripcion = LocalDateTime.now();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
