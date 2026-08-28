package pe.edu.upeu.enums;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum DiasSemana {
    DOMINGO("Domingo"),
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miercoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado");

    String nombreDia;

    DiasSemana(String nombreDia){
        this.nombreDia=nombreDia;
    }

}
