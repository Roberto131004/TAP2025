package org.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.modelos.ClientesDAO;

public class Cliente extends Stage {

    private Button btnGuardar;
    private TextField txtNombre, txtDireccion, txtTelCte, txtEmail;
    private VBox vBox;
    private Scene escena;
    private ClientesDAO objC;
    private TableView<ClientesDAO> tbvClientes;

    public Cliente(TableView<ClientesDAO> tbvCte) {
        this.tbvClientes = tbvCte;
        objC = new ClientesDAO();
        CrearUI();
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){

        txtNombre = new TextField();
        txtDireccion = new TextField();
        txtTelCte = new TextField();
        txtEmail = new TextField();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
           objC.setNomCte(txtNombre.getText());
           objC.setDireccion(txtDireccion.getText());
           objC.setTelCte(txtTelCte.getText());
           objC.setEmailCte(txtEmail.getText());
           objC.INSERT();
           tbvClientes.setItems(objC.SELECT());
           tbvClientes.refresh();
           this.close();
        });
        vBox = new VBox(txtNombre, txtDireccion, txtTelCte, txtEmail, btnGuardar);
        escena = new Scene(vBox,120,150);
    }
}
