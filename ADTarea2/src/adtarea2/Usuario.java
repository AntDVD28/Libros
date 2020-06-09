/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea2;

/**
 *
 * @author AntDVD
 */
public class Usuario {
    
    private final String dni;
    private final String nombre;
    private final String apellidos;
    private final Integer edad;
    private final String direccion;
    private final String telefono;
    
    public Usuario(String dni, String nombre, String apellidos, Integer edad, String direccion, String telefono ){
        
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    
}
