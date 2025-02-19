package org.example.tap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class Calculadora extends Stage {

    private Scene escena;
    private TextField txtDisplay;
    private VBox vBox;
    private GridPane gdpTeclado;
    private Button[][] arBtnTeclado;
    private String strTeclas[] = {"7","8","9","/","4","5","6","*","1","2","3","-","C","0","=","+"};
    private boolean nuevoNumero = true; // Indica si se debe limpiar el display después de un resultado

    public void CrearUI(){
        CrearKeyboard();
        txtDisplay = new TextField("0");
        txtDisplay.setEditable(false);
        txtDisplay.setAlignment(Pos.BASELINE_RIGHT);
        txtDisplay.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");
        vBox = new VBox(txtDisplay, gdpTeclado);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena = new Scene(vBox, 250, 300);
    }

    public void CrearKeyboard(){
        arBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(5);
        gdpTeclado.setVgap(5);
        gdpTeclado.setAlignment(Pos.CENTER);

        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arBtnTeclado[i][j] = new Button(strTeclas[pos]);
                arBtnTeclado[i][j].setStyle("-fx-font-size: 16px; -fx-pref-width: 50px; -fx-pref-height: 50px;");
                int finalPos = pos;
                arBtnTeclado[i][j].setOnAction(event -> EventoTeclado(strTeclas[finalPos]));
                gdpTeclado.add(arBtnTeclado[i][j], j, i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String strTecla) {
        if (strTecla.equals("=")) {
            calcularResultado();
        } else if (strTecla.equals("C")) {
            txtDisplay.setText("0");
            nuevoNumero = true;
        } else {
            if (nuevoNumero) {
                txtDisplay.setText("");
                nuevoNumero = false;
            }
            txtDisplay.appendText(strTecla);
        }
    }

    private void calcularResultado() {
        String expresion = txtDisplay.getText();

        //  no termine en un operador
        if (expresion.endsWith("+") || expresion.endsWith("-") || expresion.endsWith("*") || expresion.endsWith("/")) {
            txtDisplay.setText("Error");
            return;
        }

        //  solo contenga números y operadores permitidos
        if (!expresion.matches("[0-9+\\-*/.]+")) {
            txtDisplay.setText("Error");
            return;
        }

        //  no haya operadores consecutivos
        if (expresion.matches(".*[+\\-*/]{2,}.*")) {
            txtDisplay.setText("Error");
            return;
        }

        // división por cero
        if (expresion.contains("/0")) {
            txtDisplay.setText("NaN");
            return;
        }

        // Evaluar expresión si pasa todas las validaciones
        double resultado = evaluarExpresion(expresion);

        if (Double.isNaN(resultado) || Double.isInfinite(resultado)) {
            txtDisplay.setText("Error");
        } else {
            txtDisplay.setText(String.valueOf(resultado));
            nuevoNumero = true;
        }
    }


    private double evaluarExpresion(String expresion) {
        return evaluar(expresion.replaceAll("÷", "/").replaceAll("×", "*"));
    }

    private double evaluar(String expresion) {
        Stack<Double> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();
        char[] chars = expresion.toCharArray();
        StringBuilder numBuffer = new StringBuilder();

        for (char ch : chars) {
            if (Character.isDigit(ch) || ch == '.') {
                numBuffer.append(ch);
            } else {
                if (numBuffer.length() > 0) {
                    valores.push(Double.parseDouble(numBuffer.toString()));
                    numBuffer.setLength(0);
                }
                while (!operadores.isEmpty() && prioridad(operadores.peek()) >= prioridad(ch)) {
                    valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
                }
                operadores.push(ch);
            }
        }
        if (numBuffer.length() > 0) {
            valores.push(Double.parseDouble(numBuffer.toString()));
        }
        while (!operadores.isEmpty()) {
            valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
        }
        return valores.pop();
    }

    private int prioridad(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        return 0;
    }

    private double aplicarOperacion(char operador, double b, double a) {
        switch (operador) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return (b != 0) ? a / b : Double.NaN;
            default: return 0;
        }
    }

    public Calculadora(){
        CrearUI();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();
    }
}
