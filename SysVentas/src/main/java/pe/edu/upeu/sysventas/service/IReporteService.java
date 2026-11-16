package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.components.reporte.Reporte;

import java.util.List;


public interface IReporteService {
    /** Reporte de ventas por rango de fechas (filtros: tipo de documento, cliente). */
    Reporte reporteVentas();

    /** Inventario valorizado, agrupado por categoría. */
    Reporte reporteInventario();

    /** Todos los reportes que se ofrecen en la pantalla de reportes. */
    List<Reporte> reportesDisponibles();
}
