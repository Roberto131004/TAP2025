package org.example.tap2025;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.modelos.Conexion;
import org.example.tap2025.vistas.Calculadora;
import org.example.tap2025.vistas.VentasRestaurante;


import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, menCompetencia2;
    private MenuItem mitCalculadora, mitRestaurante;
    private Scene escena;

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction(event -> new VentasRestaurante());
        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora, mitRestaurante);
        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompentencia1);
        vBox = new VBox(mnbPrincipal);

        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/styles/calcu.css").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Conexion.createConexion();
        CrearUI();
        stage.setTitle("Hola Mundo de Eventos :)");
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
    void clickEvent(){
        System.out.println("Evento desde un metodo :)");
    }
}
