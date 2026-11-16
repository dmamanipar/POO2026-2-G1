package pe.edu.upeu.sysventas.repository;

import pe.edu.upeu.sysventas.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReporteRepository extends SqlHelper<Object[]> {

    @Override
    protected Object[] mapRow(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        Object[] fila = new Object[md.getColumnCount()];
        for (int i = 0; i < fila.length; i++) fila[i] = rs.getObject(i + 1);
        return fila;
    }

    /**
     * Columnas: fecha, documento (serie-número), tipo, cliente, base, igv, total.
     * Los filtros tipoDoc y cliente son opcionales (null = sin filtrar).
     */
    public List<Object[]> ventasPorFecha(LocalDate desde, LocalDate hasta, String tipoDoc, String cliente) {
        StringBuilder sql = new StringBuilder(
                "SELECT v.fecha_gener, v.serie || '-' || v.num_doc, v.tipo_doc, c.nombres, " +
                "v.precio_base, v.igv, v.precio_total " +
                "FROM venta v JOIN cliente c ON c.dniruc = v.dniruc " +
                "WHERE v.fecha_gener BETWEEN ? AND ? ");
        List<Object> args = new ArrayList<>(List.of(java.sql.Date.valueOf(desde), java.sql.Date.valueOf(hasta)));
        if (tipoDoc != null) { sql.append("AND UPPER(v.tipo_doc) = UPPER(?) "); args.add(tipoDoc); }
        if (cliente != null) { sql.append("AND UPPER(c.nombres) LIKE UPPER(?) "); args.add("%" + cliente + "%"); }
        sql.append("ORDER BY v.fecha_gener, v.id_venta");
        return executeQuery(sql.toString(), args.toArray());
    }

    /**
     * Columnas: categoría, producto, stock, precio unitario, valor (stock * pu).
     * Todos los filtros son opcionales (null / false = sin filtrar).
     */
    public List<Object[]> inventario(String producto, Double stockMax, boolean soloStockBajo, double umbralBajo) {
        StringBuilder sql = new StringBuilder(
                "SELECT ca.nombre, pr.nombre, pr.stock, pr.pu, pr.stock * pr.pu " +
                "FROM producto pr JOIN categoria ca ON ca.id_categoria = pr.id_categoria WHERE 1=1 ");
        List<Object> args = new ArrayList<>();
        if (producto != null) { sql.append("AND UPPER(pr.nombre) LIKE UPPER(?) "); args.add("%" + producto + "%"); }
        if (stockMax != null) { sql.append("AND pr.stock <= ? "); args.add(stockMax); }
        if (soloStockBajo) { sql.append("AND pr.stock <= ? "); args.add(umbralBajo); }
        sql.append("ORDER BY ca.nombre, pr.nombre");
        return executeQuery(sql.toString(), args.toArray());
    }
}
