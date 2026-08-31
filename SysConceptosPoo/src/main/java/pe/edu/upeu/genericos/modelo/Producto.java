package pe.edu.upeu.genericos.modelo;

import lombok.Data;

@Data
public class Producto {
    String codigo;
    String nombre;
    double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
}
