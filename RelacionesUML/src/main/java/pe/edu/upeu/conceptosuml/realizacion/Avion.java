package pe.edu.upeu.conceptosuml.realizacion;

public class Avion implements Volador{
    @Override
    public void volar() {
        System.out.println("El Avion vuela sobre las nuves");
    }

    @Override
    public void correr() {
        System.out.println("El Avion antes de despegar corre");
    }
}
