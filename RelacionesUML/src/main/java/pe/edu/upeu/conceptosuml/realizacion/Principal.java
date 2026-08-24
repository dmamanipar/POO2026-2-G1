package pe.edu.upeu.conceptosuml.realizacion;

public class Principal {

    public static void main(String[] args) {
        Volador volador=new Avion();
        volador.correr();
        volador.volar();
        volador=new Pajaro();
        volador.correr();
        volador.volar();
    }
}
