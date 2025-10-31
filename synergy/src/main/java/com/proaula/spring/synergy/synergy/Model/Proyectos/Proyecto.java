package com.proaula.spring.synergy.synergy.Model.Proyectos;


import java.util.ArrayList;
import java.util.List;

public class Proyecto {

    public enum EstadoProyecto{
        CANCELADO, EN_PROGRESO, TERMINADO
    }

    private static int contador = 1;

    private int idProyecto;
    private String nombre;
    private String categoria;
    private EstadoProyecto estado;
    private List<Comentario> comentarios;
    private List<Tarea> tareas;

    public Proyecto(String nombre, String categoria) {
        this.idProyecto = contador++;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = EstadoProyecto.EN_PROGRESO; // por defecto
        this.comentarios = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }
}
