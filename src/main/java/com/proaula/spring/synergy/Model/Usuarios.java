package com.proaula.spring.synergy.Model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, unique = true, length = 45)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    public enum Rol {
        Administrador,
        Participante,
        Lider
    }

    // Relación inversa ManyToMany (NO toca tu tabla, NO cambia nada)
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private Set<Tarea> tareas = new HashSet<>();

    public Usuarios() {}

    public Usuarios(String nombre, String correo, String contrasena, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public Set<Tarea> getTareas() { return tareas; }
    public void setTareas(Set<Tarea> tareas) { this.tareas = tareas; }
}
