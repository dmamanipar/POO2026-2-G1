package pe.edu.upeu.conceptosuml.realizacion;

public class Pajaro implements Volador {
    @Override
    public void volar() {
        System.out.println("El pajaro vuela");
    }
    @Override
    public void correr() {
        System.out.println("El Pajaro corre para volar");
    }
}
