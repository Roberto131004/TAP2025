package org.example.tap2025;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.modelos.Conexion;
import org.example.tap2025.vistas.Calculadora;
import org.example.tap2025.vistas.ListaClientes;
import org.example.tap2025.vistas.Rompecabezas; // Importamos el juego
import org.example.tap2025.vistas.VentasRestaurante;

import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, menCompetencia2;
    private MenuItem mitCalculadora, mitRestaurante, mitRompecabezas; // Agregamos el item del rompecabezas
    private Scene escena;

    void CrearUI(){
        // Opción del menú "Calculadora"
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());

        // Opción del menú "Restaurante"
        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction(event -> new ListaClientes());

        // Nueva opción del menú "Rompecabezas"
        mitRompecabezas = new MenuItem("Rompecabezas");
        mitRompecabezas.setOnAction(event -> abrirRompecabezas()); // Llamamos al método para abrir el juego

        // Agregamos las opciones a "Competencia 1"
        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora, mitRestaurante, mitRompecabezas); // Se agrega el nuevo item

        // Barra de menú principal
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
        System.out.println("Evento desde un método :)");
    }

    // Método para abrir el rompecabezas en una nueva ventana
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
