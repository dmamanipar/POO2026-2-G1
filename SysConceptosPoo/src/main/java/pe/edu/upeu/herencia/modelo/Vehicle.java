package pe.edu.upeu.herencia.modelo;

import lombok.Data;


@Data
public  class Vehicle{
    protected  String marca="Ford";

    public void sonido(){
        System.out.println("Tuut, tuut!");
    }
}
