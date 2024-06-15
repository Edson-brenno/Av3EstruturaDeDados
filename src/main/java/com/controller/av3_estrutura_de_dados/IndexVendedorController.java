package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.application.Platform;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;

public class IndexVendedorController implements Initializable, Controller {
    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private PilhaProdutos pilhaProdutos=null;
    @FXML
    private Label labelNomeUsuario;

    @Override
    public void setListaClientes(ListaClientes listaClientes){
        System.out.println("Chegou");
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos){
        this.pilhaProdutos = pilhaProdutos;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
