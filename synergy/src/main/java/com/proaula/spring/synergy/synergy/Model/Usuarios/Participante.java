package com.proaula.spring.synergy.synergy.Model.Usuarios;

public class Participante {

    private int idUsuario;  // referencia al mismo usuario
    private String idProyectoParticipacion;

    public Participante(int idUsuario, String idProyectoParticipacion) {
        this.idUsuario = idUsuario;
        this.idProyectoParticipacion = idProyectoParticipacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getIdProyectoParticipacion() {
        return idProyectoParticipacion;
    }

    public void setIdProyectoParticipacion(String idProyectoParticipacion) {
        this.idProyectoParticipacion = idProyectoParticipacion;
    }

    public void mostrarDatosParticipacion() {
        System.out.println("Usuario ID: " + idUsuario + " participa en el proyecto: " + idProyectoParticipacion);
    }
}
