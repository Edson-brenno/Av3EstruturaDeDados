package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import com.views.av3_estrutura_de_dados.Login;
import javafx.stage.Stage;

public class CadastroController {
    @FXML
    private Button btnVoltar;

    @FXML
    public void onBtnVoltarClickAction(ActionEvent event) throws IOException {
        try{
            Parent root = FXMLLoader.load(Login.class.getResource("Login-view.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }
}
