package pe.edu.upeu.claseinterna;

public class ClaseExt {
    class ClaIntUno{
        void saludo(){
            System.out.println("soy Clase Interna Uno");
        }
    }
    class ClaIntDos{
       void saludo(){
            System.out.println("soy Clase Interna Dos");
        }
        static void mostrarInformacion(){
            System.out.println("Los metodos staticos se pueden llamar de forma directa");
        }
    }
    public static void main(String[] args) {
        ClaseExt ce=new ClaseExt();
        ClaIntUno cIU=ce.new ClaIntUno();
        cIU.saludo();
        ClaIntDos cID=ce.new ClaIntDos();
        cID.saludo();
        ClaIntDos.mostrarInformacion();
    }
}
