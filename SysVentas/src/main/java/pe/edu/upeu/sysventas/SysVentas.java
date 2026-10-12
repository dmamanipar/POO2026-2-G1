package pe.edu.upeu.sysventas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pe.edu.upeu.sysventas.config.AppContext;
import pe.edu.upeu.sysventas.config.DatabaseConfig;
import pe.edu.upeu.sysventas.controller.ProductoController;

import java.io.IOException;

public class SysVentas extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppContext context=AppContext.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        //Screen screen=Screen.getPrimary();
        //Rectangle2D r2d=screen.getVisualBounds();
        //Scene scene = new Scene(fxmlLoader.load(), r2d.getWidth(), r2d.getHeight()-50);
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Sistema de ventas!");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Libera las conexiones del pool al cerrar la ventana.
        DatabaseConfig.shutdown();
    }
}
