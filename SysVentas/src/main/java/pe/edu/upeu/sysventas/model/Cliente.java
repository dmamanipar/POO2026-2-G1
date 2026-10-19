package pe.edu.upeu.sysventas.model;

import lombok.Builder;
import lombok.Data;
import pe.edu.upeu.sysventas.enums.TipoDocumento;


@Builder
@Data
public class Cliente {
    String dniruc;
    String nombres;
    String repLegal;
    String direccion;
    TipoDocumento tipoDocumento;
}
