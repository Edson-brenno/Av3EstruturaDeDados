package com.views.av3_estrutura_de_dados.util;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.Locale;

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

    // Método para validar campo de email
    public static void setTextFieldEmail(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.matches(
                    "^(?=[a-zA-Z0-9@._%+-]{6,254}$)([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$")){
                tf.setStyle("-fx-border-color: red;");

            }else{
                tf.setStyle("-fx-border-color: none;");

            }
        });
    }

    // Método para validar campos de textField que tem que ser sem acento
    public static void setTextFieldSemAcento(TextField tf) {
        tf.textProperty().addListener((observable, oldvalue, newValue) -> {
            if(newValue != null && !newValue.matches("^[a-zA-Z0-9 _.-]*$")) {
                tf.setText(oldvalue);
            }else {
                tf.setText(newValue.toLowerCase(Locale.ROOT));
            }
        });
    }

    // Método para limitar o tamanho do textArea de descricao
    public static void setTextAreaDescricao(TextArea tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() > 2500){
                tf.setText(oldValue);
            }
        });
    }
}
