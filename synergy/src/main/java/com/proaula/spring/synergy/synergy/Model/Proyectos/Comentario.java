package com.proaula.spring.synergy.synergy.Model.Proyectos;

import java.time.LocalDateTime;

public class Comentario {
        public enum EstadoComentario{
        CANCELADO, EN_PROGRESO, TERMINADO
    }

    private String autor;
    private String contenido;
    private LocalDateTime fecha;
    private EstadoComentario estado;
    

    public Comentario(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void mostrarComentario() {
        System.out.println("[" + fecha + "] " + autor + ": " + contenido);
    }
}
