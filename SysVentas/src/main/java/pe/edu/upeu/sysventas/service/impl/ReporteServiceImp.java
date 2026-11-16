package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.components.reporte.ColumnaReporte;
import pe.edu.upeu.sysventas.components.reporte.ParametroReporte;
import pe.edu.upeu.sysventas.components.reporte.Reporte;
import pe.edu.upeu.sysventas.dto.SessionManager;
import pe.edu.upeu.sysventas.repository.ReporteRepository;
import pe.edu.upeu.sysventas.service.IReporteService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ReporteServiceImp implements IReporteService {

    private static final String[] EMPRESA = {"SysVentas - SysCenterLife", "Sistema de Ventas e Inventario"};
    private static final double STOCK_BAJO = 5;

    private final ReporteRepository reporteRepository;

    @Override
    public List<Reporte> reportesDisponibles() {
        return List.of(reporteVentas(), reporteInventario());
    }

    @Override
    public Reporte reporteVentas() {
        LocalDate hoy = LocalDate.now();
        return Reporte.nuevo("Reporte de Ventas")
                .subtitulo("Documentos emitidos en el periodo")
                .empresa(EMPRESA)
                .usuario(usuario())
                .rangoFechas(hoy.withDayOfMonth(1), hoy)
                .parametro(ParametroReporte.lista("tipo", "Tipo de documento", List.of("Todos", "Factura", "Boleta")))
                .parametro(ParametroReporte.texto("cliente", "Cliente contiene"))
                .orientacion(Reporte.Orientacion.HORIZONTAL)
                .columna(ColumnaReporte.fecha("Fecha", 1.2f))
                .columna(ColumnaReporte.texto("Documento", 1.6f))
                .columna(ColumnaReporte.texto("Tipo", 1.2f))
                .columna(ColumnaReporte.texto("Cliente", 4f))
                .columna(ColumnaReporte.moneda("Base", 1.5f).sumar())
                .columna(ColumnaReporte.moneda("IGV", 1.3f).sumar())
                .columna(ColumnaReporte.moneda("Total", 1.5f).sumar())
                .resumen(filas -> {
                    double total = filas.stream().mapToDouble(f -> ColumnaReporte.aNumero(f[6])).sum();
                    Map<String, String> r = new LinkedHashMap<>();
                    r.put("Documentos", String.valueOf(filas.size()));
                    r.put("Venta total", String.format("S/ %,.2f", total));
                    r.put("Promedio por documento", String.format("S/ %,.2f", total / filas.size()));
                    return r;
                })
                .notaPie("Montos expresados en soles (PEN). Incluye IGV.")
                .datos(p -> {
                    String tipo = (String) p.get("tipo");
                    return reporteRepository.ventasPorFecha(
                            (LocalDate) p.get("desde"), (LocalDate) p.get("hasta"),
                            tipo == null || "Todos".equals(tipo) ? null : tipo,
                            (String) p.get("cliente"));
                })
                .build();
    }

    @Override
    public Reporte reporteInventario() {
        return Reporte.nuevo("Inventario de Productos")
                .subtitulo("Existencias valorizadas por categoría")
                .empresa(EMPRESA)
                .usuario(usuario())
                .parametro(ParametroReporte.texto("producto", "Producto contiene"))
                .parametro(ParametroReporte.numero("stockMax", "Stock máximo"))
                .parametro(ParametroReporte.booleano("soloBajo", "Solo stock bajo (≤ " + (int) STOCK_BAJO + ")", false))
                .columna(ColumnaReporte.texto("Categoría", 2f))
                .columna(ColumnaReporte.texto("Producto", 4f))
                .columna(ColumnaReporte.decimal("Stock", 1.2f).sumar())
                .columna(ColumnaReporte.moneda("P. Unit.", 1.5f))
                .columna(ColumnaReporte.moneda("Valor", 1.7f).sumar())
                .agruparPor(0)
                .datos(p -> reporteRepository.inventario(
                        (String) p.get("producto"), (Double) p.get("stockMax"),
                        Boolean.TRUE.equals(p.get("soloBajo")), STOCK_BAJO))
                .build();
    }

    private static String usuario() {
        String u = SessionManager.getInstance().getUserName();
        return u == null ? "" : u;
    }
}
