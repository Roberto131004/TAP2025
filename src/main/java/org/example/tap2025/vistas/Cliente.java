package org.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {

    private Button btnGuardar;
    private TextField txtNombre, txtDireccion, txtTelCte, txtEmail;
    private VBox vBox;
    private Scene escena;

    public Cliente() {
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }

     private void Cliente(){
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
        vBox = new VBox(txtNombre, txtDireccion, txtTelCte, txtEmail);
        escena = new Scene(vBox,120,150);
    }
}
