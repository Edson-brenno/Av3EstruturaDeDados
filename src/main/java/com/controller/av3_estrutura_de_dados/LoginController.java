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

import com.views.av3_estrutura_de_dados.Cadastro;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.controller.av3_estrutura_de_dados.CadastroController;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.controller.av3_estrutura_de_dados.util.SetarListaClientesDoController;
import javafx.stage.Stage;

public class LoginController implements Initializable, Controller {
    @FXML
    private ListaClientes listaClientes;

    @FXML
    private TextField userName;

    @FXML
    private Label mensagemError;

    @FXML
    private Button botaoCadastro;

    @FXML
    public void onBtCadastroClickAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(Cadastro.class.getResource("Cadastro-view.fxml"));
            Parent root = loader.load();
            SetarListaClientesDoController.setarListaClientes(loader, this.listaClientes);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (RuntimeException e){
            e.getStackTrace();
        }
    }

    @FXML
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldUsuario(userName, mensagemError);
    }
}
