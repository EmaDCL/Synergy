package com.proaula.spring.synergy.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "comentario") 
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String contenido;

    private LocalDateTime fecha;

    // Relación con Proyecto
    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    // Relación con Usuario
    @ManyToOne 
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    // Se pone la fecha automáticamente
    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Optional<Usuarios> getUsuario() {
        return Optional.ofNullable(usuario);
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}