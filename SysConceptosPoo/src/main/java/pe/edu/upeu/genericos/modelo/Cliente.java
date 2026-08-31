package pe.edu.upeu.genericos.modelo;

import lombok.Data;

@Data
public class Cliente {
    String nombre;
    String dni;
    String direccion;
    String telefono;

    public Cliente(String nombre, String dni, String direccion, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
