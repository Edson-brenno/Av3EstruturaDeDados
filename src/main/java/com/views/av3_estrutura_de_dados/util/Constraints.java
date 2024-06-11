package com.views.av3_estrutura_de_dados.util;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Constraints {

    public static void setTextFieldNomeCompleto(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Não permite a utilização de numeros e caracteres especiais
            if(newValue != null && !newValue.matches("^[A-Za-z ]*$")) {
                tf.setStyle("-fx-border-color: red;");
            }else{
                tf.setStyle("-fx-border-color: none;");
            }

            // Seta tamanho Máximo
            if(newValue != null && newValue.length() > 250){
                tf.setText(oldValue);
            }

            //
            if(newValue == null || oldValue == null){
                tf.setText("");
            }

        });
    }

    // Metodo para text field usuario:
    public static void setTextFieldUsuario(TextField tf, Label labelError) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Não permite a utilização de numeros e caracteres especiais
            if(newValue != null && !newValue.matches("^[A-Za-z]*$")) {
                tf.setStyle("-fx-border-color: red;");
                if(labelError != null) {
                    labelError.setText("Error: não pode conter numeros ou caracteres");
                }
            }else{
                tf.setStyle("-fx-border-color: none;");
                if(labelError != null) {
                    labelError.setText("");
                }
            }

            // Seta tamanho Máximo
            if(newValue != null && newValue.length() > 250){
                tf.setText(oldValue);
            }

            //
            if(newValue == null || oldValue == null){
                tf.setText("");
            }
        });
    }
}
