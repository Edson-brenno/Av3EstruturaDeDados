package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginController implements Initializable {
    @FXML
    private TextField userName;

    @FXML
    private Label mensagemError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldUsuario(userName, mensagemError);
    }
}
