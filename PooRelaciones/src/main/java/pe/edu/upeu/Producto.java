package pe.edu.upeu;
//Nombre de Clase
public class Producto {

    //Atributos
    String codigo;
    String nombre;
    double precio;
    int stock;
    double igv;

    //Constructor, si hay mas de un constructor se denomina sobre carga de constructores
    public Producto(String codigo,
                    String nombre, double precio,
                    int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.igv=getIgv();
    }
    public Producto(String codigo,
                    String nombre, double precio) {
        this(codigo, nombre, precio, 0);
    }
    //Metodos
    public double getIgv() {
        if(precio<0)
            throw new IllegalArgumentException();
        return precio*0.18;
    }
}
