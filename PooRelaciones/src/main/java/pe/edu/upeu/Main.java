package pe.edu.upeu;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Producto> prod=new ArrayList<>();
        //SequencedCollection<Producto> dd=new ArrayList<>();
        prod.add(new Producto("P001", "Televisor",
                4000, 20 ));
        prod.add(new Producto("P002", "Parlantes",
                2000));
        var px=new Producto("P003", "Celular", 3500);
        var cantiad=54.5;

        prod.add(px);
        for (Producto p: prod){
            System.out.println(p.nombre+"\t"+p.precio+"\t"+
                    p.stock+"\t"+p.igv);
        }
        System.out.println(px.getClass());
        System.out.println(cantiad);
        System.out.println(cantiad instanceof double);


        var coche1=new Coche("Toyota");

        var coche2=new Coche("Ford", 150);

    }
}