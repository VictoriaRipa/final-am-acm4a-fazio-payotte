package com.example.aplicacion;
public class Usuario {
    private String nombre;
    private String email;
    

    public Usuario() {
        // Constructor vacío requerido por Firebase Realtime Database
    }

    public Usuario(String nombre, String email /* otros campos */) {
        this.nombre = nombre;
        this.email = email;

    }

    // Métodos getters y setters para los campos del usuario
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
