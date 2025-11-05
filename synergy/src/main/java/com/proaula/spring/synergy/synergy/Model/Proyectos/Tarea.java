package com.proaula.spring.synergy.synergy.Model.Proyectos;
import java.time.LocalDateTime;
public class Tarea {

        public enum Estado{
        CANCELADO, EN_PROGRESO, TERMINADO
    }

    private static int contador = 1;

    private int idTarea;
    private int ID_Proyecto_Tarea;
    private String Nombre_Tarea;
    private String descripcion;
    private Estado estado;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_Fin;
    private int ID_Participante;


    public Tarea(int idTarea, int ID_Proyecto_Tarea, String Nombre_Tarea, String descripcion, Estado estado, LocalDateTime fecha_inicio, LocalDateTime fecha_Fin, int participante) {
        this.idTarea = contador++;
        this.ID_Proyecto_Tarea = ID_Proyecto_Tarea;
        this.Nombre_Tarea = Nombre_Tarea;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_Fin = fecha_Fin;
        this.ID_Participante= participante;
    }

    public int getIdTarea() {
        return this.idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getID_Proyecto_Tarea() {
        return this.ID_Proyecto_Tarea;
    }

    public void setID_Proyecto_Tarea(int ID_Proyecto_Tarea) {
        this.ID_Proyecto_Tarea = ID_Proyecto_Tarea;
    }

    public String getNombre_Tarea() {
        return this.Nombre_Tarea;
    }

    public void setNombre_Tarea(String Nombre_Tarea) {
        this.Nombre_Tarea = Nombre_Tarea;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_inicio() {
        return this.fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_Fin() {
        return this.fecha_Fin;
    }

    public void setFecha_Fin(LocalDateTime fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    public int getID_Participante() {
        return this.ID_Participante;
    }

    public void setID_Participante(int ID_Participante) {
        this.ID_Participante = ID_Participante;
    }

}
