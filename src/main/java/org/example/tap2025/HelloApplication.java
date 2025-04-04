package org.example.tap2025;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.componentes.Hilo;
import org.example.tap2025.modelos.Conexion;
import org.example.tap2025.vistas.*;

import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, menCompetencia2;
    private MenuItem mitCalculadora, mitRestaurante, mitRompecabezas,mitHilos;
    private Scene escena;

    void CrearUI(){

        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction(event -> new ListaClientes());
        mitRompecabezas = new MenuItem("Rompecabezas");
        mitRompecabezas.setOnAction(event -> abrirRompecabezas());
        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora, mitRestaurante, mitRompecabezas);
        menCompetencia2 = new Menu("Competencia 2");
        mitHilos = new MenuItem("Celayork");
        menCompetencia2.getItems().add(mitHilos);
        mitHilos.setOnAction(event -> new Celayork());
        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompentencia1, menCompetencia2);

        vBox = new VBox(mnbPrincipal);

        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/styles/calcu.css").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        /*new Hilo("Ruta Pinos ").start();
        new Hilo("Ruta Laureles ").start();
        new Hilo("Ruta San Juan de la Vega ").start();
        new Hilo("Ruta MonteBlanco ").start();
        new Hilo("Ruta Teneria ").start();*/

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
        System.out.println("Evento desde un método :)");
    }

    private void abrirRompecabezas() {
        Stage rompecabezasStage = new Stage();
        Rompecabezas rompecabezas = new Rompecabezas();
        try {
            rompecabezas.start(rompecabezasStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
