package pe.edu.upeu.polimorfinterface;

public class Loro implements Animal{

    @Override
    public void emitirSonido() {
        System.out.println("Hola manito....aprende pues!");
    }

    @Override
    public void dormir() {
        System.out.println("Zzz...zzz..zz");
    }

    @Override
    public void comer() {
        System.out.println("Comiento manzana");
    }

    @Override
    public void pruebad() {
        System.out.println("Estamos probando que pasa");
    }
}
