package com.proaula.spring.synergy.synergy.Model.Usuarios;

public class Usuario {

    private static int contador = 1;

    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private String contraseña;
    private String rol;

    public Usuario(String nombreUsuario, String correo, String contraseña, String rol) {
        this.idUsuario = contador++;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void mostrarInformacion() {
        System.out.println("Usuario ID: " + idUsuario);
        System.out.println("Nombre: " + nombreUsuario);
        System.out.println("Correo: " + correo);
        System.out.println("Rol actual: " + rol);
    }
}
