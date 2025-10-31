package com.proaula.spring.synergy.synergy.Model.Proyectos;
public class Tarea {

        public enum EstadoTarea{
        CANCELADO, EN_PROGRESO, TERMINADO
    }

    private static int contador = 1;

    private int idTarea;
    private String titulo;
    private String descripcion;
    private String responsable; // idUsuario o nombre
    private EstadoTarea estado; // reutiliza el enum

    public Tarea(String titulo, String descripcion, String responsable) {
        this.idTarea = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estado = EstadoTarea.EN_PROGRESO; // por defecto
    }

    public int getIdTarea() {
        return idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public void mostrarTarea() {
        System.out.println("Tarea ID: " + idTarea);
        System.out.println("Título: " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Responsable: " + responsable);
        System.out.println("Estado: " + estado);
    }
}
