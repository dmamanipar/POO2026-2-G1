module pe.edu.upeu.repasog1array {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens pe.edu.upeu.repasog1array to javafx.fxml;
    exports pe.edu.upeu.repasog1array;
}