package pe.edu.upeu.sysventas.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DocumentoComercial {
    private Long id;
    private double precioBase;
    private double igv;
    private double precioTotal;
    private String serie;
    private String numDoc;
    private String tipoDoc;

    public abstract double calcularTotal();
}
