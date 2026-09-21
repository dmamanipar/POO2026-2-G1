package pe.edu.upeu.sysventas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.components.ColumnInfo;
import pe.edu.upeu.sysventas.components.TableViewHelper;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.enums.TipoProducto;
import pe.edu.upeu.sysventas.model.Producto;
import pe.edu.upeu.sysventas.service.ICategoriaService;
import pe.edu.upeu.sysventas.service.IMarcaService;
import pe.edu.upeu.sysventas.service.IProductoService;
import pe.edu.upeu.sysventas.service.IUnidadMedidaService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ProductoController {
    private final IMarcaService ms;
    private final ICategoriaService cs;
    private final IProductoService ps;
    private final IUnidadMedidaService ums;

    @FXML ComboBox<ComboBoxOption> cbxTipoProducto;
    @FXML ComboBox<ComboBoxOption> cbxCategoria, cbxMarca, cbxUnidadMedida;
    @FXML TextField txtNombreProducto, txtPUnit,
            txtPUnitOld, txtUtilidad, txtStock, txtStockOld, txtFiltroDato;

    @FXML private TableView<Producto> tableView;
    ObservableList<Producto> listarProducto;

    Producto formulario;
    Long idProductoCE = 0L;

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
        Consumer<Producto> updateAction= p->{
            editForm(p);
            idProductoCE=p.getIdProducto();
        };
        Consumer<Producto> deleteAction= p->{
            ps.delete(p.getIdProducto());
            //double w = stage.getWidth() / 1.5, h = stage.getHeight() / 2;
            //Toast.showToast(stage, "Se eliminó correctamente!!", 2000, w, h);
            listar();
        };

        tableViewHelper.addColumnsInOrderWithSize(tableView, columns,
                updateAction, deleteAction);
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

    @FXML
    public void validarFormulario() {
        formulario = new Producto();
        formulario.setNombre(txtNombreProducto.getText());
        formulario.setPu(parseDoubleSafe(txtPUnit.getText()));
        formulario.setPuold(parseDoubleSafe(txtPUnitOld.getText()));
        formulario.setUtilidad(parseDoubleSafe(txtUtilidad.getText()));
        formulario.setStock(parseDoubleSafe(txtStock.getText()));
        formulario.setStockold(parseDoubleSafe(txtStockOld.getText()));

        String idxTP = cbxTipoProducto.getSelectionModel().getSelectedItem() == null ? ""
                : cbxTipoProducto.getSelectionModel().getSelectedItem().getKey();
        formulario.setTipoProducto(idxTP.equals("") ? null : TipoProducto.valueOf(idxTP));

        String idxM = cbxMarca.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxMarca.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdMarca(idxM.equals("0") ? null : ms.findById(Long.parseLong(idxM)));

        String idxC = cbxCategoria.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxCategoria.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdCategoria(idxC.equals("0") ? null : cs.findById(Long.parseLong(idxC)));

        String idxUM = cbxUnidadMedida.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxUnidadMedida.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdUnidad(idxUM.equals("0") ? null : ums.findById(Long.parseLong(idxUM)));

        if(idProductoCE==0) {
            ps.save(formulario);
        }else{
            formulario.setIdProducto(idProductoCE);
            ps.update(idProductoCE, formulario);
            idProductoCE=0L;
        }
        clearForm();

        listar();

        /* Set<ConstraintViolation<Producto>> violaciones = validator.validate(formulario);
        List<ConstraintViolation<Producto>> violacionesOrdenadas = violaciones.stream()
                .sorted(Comparator.comparing(v -> v.getPropertyPath().toString())).toList();

        if (violacionesOrdenadas.isEmpty()) {
            procesarFormulario();

        } else {
            mostrarErroresValidacion(violacionesOrdenadas);
        } */

    }

    private double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try { return Double.parseDouble(value.trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }


    public void editForm(Producto producto) {
        txtNombreProducto.setText(producto.getNombre());

        txtPUnit.setText(producto.getPu().toString());
        txtPUnitOld.setText(producto.getPuold().toString());
        txtUtilidad.setText(producto.getUtilidad().toString());
        txtStock.setText(producto.getStock().toString());
        txtStockOld.setText(producto.getStockold().toString());

        cbxTipoProducto.getSelectionModel().select(
                cbxTipoProducto.getItems().stream()
                        .filter(m -> m.getKey() == producto.getTipoProducto().name())
                        .findFirst().orElse(null));

        cbxMarca.getSelectionModel().select(
                cbxMarca.getItems().stream()
                        .filter(m -> Long.parseLong(m.getKey()) == producto.getIdMarca().getIdMarca())
                        .findFirst().orElse(null));
        cbxCategoria.getSelectionModel().select(
                cbxCategoria.getItems().stream()
                        .filter(c -> Long.parseLong(c.getKey()) == producto.getIdCategoria().getIdCategoria())
                        .findFirst().orElse(null));
        cbxUnidadMedida.getSelectionModel().select(
                cbxUnidadMedida.getItems().stream()
                        .filter(u -> Long.parseLong(u.getKey()) == producto.getIdUnidad().getIdUnidad())
                        .findFirst().orElse(null));
        idProductoCE = producto.getIdProducto();
        limpiarError();
    }

    public void limpiarError() {
        List.of(txtNombreProducto,
                        cbxTipoProducto,
                        txtPUnit, txtPUnitOld, txtUtilidad,
                        txtStock, txtStockOld, cbxMarca, cbxCategoria, cbxUnidadMedida)
                .forEach(c -> {c.getStyleClass().remove("text-field-error");
                   // ttc.limpiarCampo(c);
                });
    }

    public void clearForm() {
        txtNombreProducto.clear();
        cbxTipoProducto.getSelectionModel().clearSelection();
        txtPUnit.clear(); txtPUnitOld.clear();
        txtUtilidad.clear(); txtStock.clear(); txtStockOld.clear();
        cbxMarca.getSelectionModel().clearSelection();
        cbxCategoria.getSelectionModel().clearSelection();
        cbxUnidadMedida.getSelectionModel().clearSelection();
        idProductoCE = 0L;
        limpiarError();
    }


}
