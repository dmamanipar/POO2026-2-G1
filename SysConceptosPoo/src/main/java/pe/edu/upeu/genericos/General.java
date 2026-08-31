package pe.edu.upeu.genericos;

import pe.edu.upeu.genericos.modelo.Cliente;
import pe.edu.upeu.genericos.modelo.Producto;
import pe.edu.upeu.genericos.repository.ClienteRepo;
import pe.edu.upeu.genericos.repository.ProductoRepo;
import pe.edu.upeu.genericos.repository.Repositorio;

public class General {
    public static void main(String[] args) {
        Repositorio<Producto> repo=new ProductoRepo();
        repo.guardar(new Producto("12345678", "Laptop 1", 100000.00));
        repo.guardar(new Producto("12345678", "Laptop 2", 200000.00));
        repo.guardar(new Producto("12345678", "Laptop 3", 300000.00));

        for (Producto p: repo.listarTodos()){
            System.out.println(p.getNombre()+" "+p.getPrecio());
        }

     Repositorio<Cliente>  cli =new ClienteRepo();
        cli.guardar(new Cliente("David", "43631917", "Perez", "951782520"));
        cli.guardar(new Cliente("Elias", "43631918", "Perez", "951782521"));

        for (Cliente c: cli.listarTodos()){
            System.out.println(c.getNombre()+" "+c.getTelefono());
        }
    }
}
