package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.util.Constraints;
import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import com.views.av3_estrutura_de_dados.Login;
import javafx.stage.Stage;

public class CadastroController implements Initializable {

    @FXML
    private TextField nomeCompleto, email, nomeUsuario, senha;
    @FXML
    private Button btnVoltar;
    @FXML
    private ComboBox<TipoClienteEnum> comboTipoCliente;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.comboTipoCliente.getItems().addAll(TipoClienteEnum.values());

      Constraints.setTextFieldNomeCompleto(this.nomeCompleto);
      Constraints.setTextFieldUsuario(this.nomeUsuario, null);
    }
}
