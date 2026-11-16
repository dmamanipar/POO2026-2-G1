package pe.edu.upeu.sysventas.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.components.reporte.ReportePanel;
import pe.edu.upeu.sysventas.service.IReporteService;
@RequiredArgsConstructor
public class ReporteController {

    @FXML private StackPane contenedor;

    private final IReporteService reporteService;


    @FXML
    public void initialize() {
        contenedor.getChildren().add(new ReportePanel(reporteService.reportesDisponibles()));
    }
}
