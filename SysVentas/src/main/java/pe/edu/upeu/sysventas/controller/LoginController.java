package pe.edu.upeu.sysventas.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.components.StageManager;
import pe.edu.upeu.sysventas.components.Toast;
import pe.edu.upeu.sysventas.config.AppContext;
import pe.edu.upeu.sysventas.config.DatabaseConfig;
import pe.edu.upeu.sysventas.dto.SessionManager;
import pe.edu.upeu.sysventas.model.Usuario;
import pe.edu.upeu.sysventas.service.IUsuarioService;

@RequiredArgsConstructor
public class LoginController {

      final private IUsuarioService u;

      @FXML TextField usuario;
      @FXML PasswordField clave;


      @FXML
      public void initialize(){

      }

       @FXML
      public void login(ActionEvent e){
          try {
              u.loginUsuario(usuario.getText(), clave.getText())
                      .ifPresentOrElse(
                              usu->{
                                  abrirMain(e, usu);
                              },
                              ()->{
                                    mensajeError(e,"Error en las credenciales...intente nuevamente!!");
                              });

          } catch (Exception ex) {
              throw new RuntimeException(ex);
          }
      }


      public void mensajeError(ActionEvent e, String msg){
          Stage stage= (Stage) ((Node)e.getSource()).getScene().getWindow();
          double w = stage.getWidth() / 1.5, h = stage.getHeight() / 2;
          Toast.showToast(stage, msg, "#FEA1B6", 2000, w, h);
      }

      private void abrirMain(ActionEvent event, Usuario usu){
          try {

              SessionManager.getInstance().setUserId(usu.getIdUsuario());
              SessionManager.getInstance().setUserName(usu.getUsuario());
              SessionManager.getInstance().setUserPerfil(usu.getIdPerfil().getNombre());

              AppContext context=AppContext.getInstance();
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/maingui.fxml"));
              fxmlLoader.setControllerFactory(context::getBean);
              Screen screen=Screen.getPrimary();
              Rectangle2D r2d=screen.getVisualBounds();
              Scene scene = new Scene(fxmlLoader.load(), r2d.getWidth(), r2d.getHeight()-50);

              scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

              Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();

              stage.setTitle("Sistema de ventas!");
              stage.setResizable(true);
              stage.setScene(scene);
              stage.setX(r2d.getMinX());
              stage.setY(r2d.getMinY());
              StageManager.setPrimaryStage(stage);
              stage.setWidth( r2d.getWidth());
              stage.setHeight(r2d.getHeight());

              stage.show();

          } catch (Exception e) {
              throw new RuntimeException(e);
          }

      }

      @FXML
      public void logout(ActionEvent e){
          Platform.exit();
          System.exit(0);
          DatabaseConfig.shutdown();
      }



}
