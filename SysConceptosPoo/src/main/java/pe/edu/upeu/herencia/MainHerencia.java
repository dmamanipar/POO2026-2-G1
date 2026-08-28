package pe.edu.upeu.herencia;

public class MainHerencia {

    public static void main(String[] args) {
        Car cObj=new Car();
        cObj.setMarca("Toyota");
        System.out.println(cObj.getMarca());
        cObj.sonido();
    }

}
