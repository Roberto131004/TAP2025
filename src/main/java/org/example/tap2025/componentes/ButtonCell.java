package org.example.tap2025.componentes;

import javafx.scene.control.*;
import org.example.tap2025.modelos.ClientesDAO;

import java.util.Optional;

public class ButtonCell extends TableCell <ClientesDAO,String>{

    private Button btnCelda;
    private String strLabelBtn;


    public ButtonCell(String label) {

        strLabelBtn = label;
        btnCelda = new Button(strLabelBtn);
        btnCelda.setOnAction(e -> {
            ClientesDAO objC = this.getTableView().getItems().get(this.getIndex());

            if(strLabelBtn.equals("Editar")) {

            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :) ");
                alert.setContentText("¿Deseas eliminar el registro seleccionado ?");
                Optional<ButtonType> opcion = alert.showAndWait();
                if(opcion.get() == ButtonType.OK){
                    objC.DELETE();
                }

            }
            this.getTableView().setItems(objC.SELECT());
            this.getTableView().refresh();
        });

    }

    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)
            this.setGraphic(btnCelda);
    }

}
