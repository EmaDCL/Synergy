package com.proaula.spring.synergy.synergy.Model.Usuarios;

public class Administrador {

    private int idUsuario;  // referencia al usuario original
    private String permisos; // opcional, para definir tipo de acceso

    public Administrador(int idUsuario, String permisos) {
        this.idUsuario = idUsuario;
        this.permisos = permisos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public void mostrarDatosAdministrador() {
        System.out.println("Usuario ID: " + idUsuario + " con permisos: " + permisos);
    }
}
