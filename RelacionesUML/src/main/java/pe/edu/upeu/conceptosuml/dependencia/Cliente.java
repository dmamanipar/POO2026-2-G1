package pe.edu.upeu.conceptosuml.dependencia;

import pe.edu.upeu.conceptosuml.asociacion.Especialidad;

public class Cliente {

    public void realizarPago(Paypal paypal, double monto){
        paypal.pagar(monto);
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Paypal paypal = new Paypal();
        cliente.realizarPago(paypal, 1000);
    }
}
