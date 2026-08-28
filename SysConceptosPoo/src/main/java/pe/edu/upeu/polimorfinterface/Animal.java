package pe.edu.upeu.polimorfinterface;

public interface Animal {
     void emitirSonido();
     void dormir();
     void comer();
     static void prueba(){
          System.out.println("probando");
     }
     default void pruebad(){
          System.out.println("como default");
     }
}
