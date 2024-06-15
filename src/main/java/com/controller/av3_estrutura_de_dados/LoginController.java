package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import com.views.av3_estrutura_de_dados.Cadastro;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.IndexVendedor;
import javafx.application.Platform;

public class LoginController implements Initializable, Controller {
    @FXML
    private ListaClientes listaClientes=null;

    @FXML
    private PilhaProdutos pilhaProdutos=null;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label mensagemError;

    @FXML
    private Button botaoCadastro, btnLogin;

    @FXML
    public void onBtnLoginClickAction(ActionEvent event) throws IOException {
        try {
            // Se o usuário existe
            if((this.listaClientes != null) && (this.listaClientes.LogarCliente(this.emailTextField.getText(),
                    this.passwordTextField.getText()) == true)){
                CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml", this.listaClientes);
            }
            else{ // Error no login
                System.out.println("Error ao logar");
            }

        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtCadastroClickAction(ActionEvent event) throws IOException {
        try {
            CarregarPagina.trocarPagina(event, Cadastro.class, "Cadastro-view.fxml", this.listaClientes);
//
        }catch (RuntimeException e){
            e.getStackTrace();
        }
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos){
        this.pilhaProdutos = pilhaProdutos;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Constraints.setTextFieldEmail(this.emailTextField);

        Platform.runLater(this::mostrarClientes);
    }

    private void mostrarClientes(){
        System.out.println("=======================================");
        this.listaClientes.MostrarClientes();
    }
}
