package pe.edu.upeu;

public class Coche {
    String marca;
    int velocidad;

    Coche(String marca, int velocidadInicial) {
        this.marca = marca;
        this.velocidad = velocidadInicial;
    }

    Coche(String marca) {
        this(marca, 0);
    }

    void acelerar() {
        velocidad = velocidad + 10;
    }

    void frenar() {
        velocidad = velocidad - 10;
    }

    void mostrarEstado() {
        System.out.println(marca + " - Velocidad: " + velocidad);
    }
}