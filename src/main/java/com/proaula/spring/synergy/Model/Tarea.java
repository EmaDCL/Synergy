package com.proaula.spring.synergy.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descripcion;

    private String estado;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    // Relación correcta con Proyecto (usa id_proyecto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    // Relación muchos a muchos con usuarios
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tarea_usuarios",
        joinColumns = @JoinColumn(name = "tarea_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuarios> usuarios = new HashSet<>();

    public Tarea() {}

    public Tarea(String titulo, String descripcion, String estado,
                 LocalDate fechaEntrega, Proyecto proyecto, Set<Usuarios> usuarios) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.proyecto = proyecto;
        this.usuarios = usuarios;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public Set<Usuarios> getUsuarios() { return usuarios; }
    public void setUsuarios(Set<Usuarios> usuarios) { this.usuarios = usuarios; }

    // Helpers
    public void addUsuario(Usuarios u) { this.usuarios.add(u); }
    public void removeUsuario(Usuarios u) { this.usuarios.remove(u); }
}
