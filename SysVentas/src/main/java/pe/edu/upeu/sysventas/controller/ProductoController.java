package pe.edu.upeu.sysventas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.components.ColumnInfo;
import pe.edu.upeu.sysventas.components.TableViewHelper;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.Producto;
import pe.edu.upeu.sysventas.service.ICategoriaService;
import pe.edu.upeu.sysventas.service.IMarcaService;
import pe.edu.upeu.sysventas.service.IProductoService;
import pe.edu.upeu.sysventas.service.IUnidadMedidaService;

import java.util.LinkedHashMap;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ProductoController {
    private final IMarcaService ms;
    private final ICategoriaService cs;
    private final IProductoService ps;
    private final IUnidadMedidaService ums;

    @FXML ComboBox<ComboBoxOption> cbxTipoProducto;
    @FXML ComboBox<ComboBoxOption> cbxCategoria, cbxMarca, cbxUnidadMedida;

    @FXML private TableView<Producto> tableView;
    ObservableList<Producto> listarProducto;

    @FXML
    public void initialize(){
        System.out.println("Holasss");
        cbxTipoProducto.getItems().addAll(ps.listarTipoProducto());

        cbxCategoria.getItems().addAll(cs.lisCategoria());
        cbxMarca.getItems().addAll(ms.listarCombobox());
        cbxUnidadMedida.getItems().addAll(ums.listarCombobox());

        TableViewHelper<Producto> tableViewHelper=new TableViewHelper<>();
        LinkedHashMap<String, ColumnInfo> columns=new LinkedHashMap<>();
        columns.put("ID Prod.", new ColumnInfo("idProducto", 60.0));
        columns.put("Tipo Producto", new ColumnInfo("tipoProducto", 150.0));
        columns.put("Nombre", new ColumnInfo("nombre", 200.0));
        Producto producto;
        Consumer<Producto> updateAction= p->{};

        tableViewHelper.addColumnsInOrderWithSize(tableView, columns,
                updateAction, updateAction);
        tableView.setTableMenuButtonVisible(true);
        listar();
    }

    public void listar(){
        try {
            tableView.getItems().clear();
            listarProducto= FXCollections.observableArrayList(ps.findAll());
            tableView.getItems().addAll(listarProducto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
