package com.proaula.spring.synergy.synergy.Model.Usuarios;

public class LiderProyecto {

    private int idUsuario;   // referencia al usuario
    private String idProyectoLidera;

    public LiderProyecto(int idUsuario, String idProyectoLidera) {
        this.idUsuario = idUsuario;
        this.idProyectoLidera = idProyectoLidera;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getIdProyectoLidera() {
        return idProyectoLidera;
    }

    public void setIdProyectoLidera(String idProyectoLidera) {
        this.idProyectoLidera = idProyectoLidera;
    }

    public void mostrarDatosLiderazgo() {
        System.out.println("Usuario ID: " + idUsuario + " lidera el proyecto: " + idProyectoLidera);
    }
}
