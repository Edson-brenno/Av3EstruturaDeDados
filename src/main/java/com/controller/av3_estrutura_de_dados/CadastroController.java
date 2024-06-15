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
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.controller.av3_estrutura_de_dados.util.SetarListaClientesDoController;
import javafx.stage.Stage;

public class CadastroController implements Initializable, Controller {

    @FXML
    private TextField nomeCompleto, email,senha;
    @FXML
    private Button btnVoltar, btnCadastrar;
    @FXML
    private ComboBox<TipoClienteEnum> comboTipoCliente;
    @FXML
    private ListaClientes listaClientes=null;

    @FXML
    public void onBtnVoltarClickAction(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            SetarListaClientesDoController.setarListaClientes(loader, this.listaClientes);
            stage.setScene(scene);
            stage.show();
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnCadastrarClickAction(ActionEvent event) throws IOException {
        try{
            if (this.nomeCompleto.getLength() > 0 &&
            this.email.getLength() > 0 && (this.comboTipoCliente.getValue() == TipoClienteEnum.CONSUMIDOR ||
                    this.comboTipoCliente.getValue() == TipoClienteEnum.VENDEDOR)){

                this.listaClientes.adicionarCliente(this.nomeCompleto.getText(), this.email.getText(),
                        this.senha.getText(),
                        this.comboTipoCliente.getValue());

                FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                SetarListaClientesDoController.setarListaClientes(loader, this.listaClientes);
                stage.setScene(scene);
                stage.show();

            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.comboTipoCliente.getItems().addAll(TipoClienteEnum.values());

      Constraints.setTextFieldNomeCompleto(this.nomeCompleto);
    }
}
