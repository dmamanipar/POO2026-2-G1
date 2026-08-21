package pe.edu.upeu;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Producto> prod=new ArrayList<>();

        prod.add(new Producto("P001", "Televisor",
                4000, 20 ));
        prod.add(new Producto("P002", "Parlantes",
                2000));

        for (Producto p: prod){
            System.out.println(p.nombre+"\t"+p.precio+"\t"+
                    p.stock+"\t"+p.igv);
        }


    }
}