package pe.edu.upeu.abstracpolimorf;

public abstract class Animal {
    public void sonidoAnimal(){
        System.out.println("El animal hace sonido");
    }

    static void prubaS(){
        System.out.println("probando statico");
    }

    public abstract void dormir();

}
