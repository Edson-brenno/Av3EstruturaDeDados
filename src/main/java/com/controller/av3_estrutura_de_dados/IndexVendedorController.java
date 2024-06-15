package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import java.net.URL;

public class IndexVendedorController implements Initializable, Controller {
    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private Label labelNomeUsuario;

    @Override
    public void setListaClientes(ListaClientes listaClientes){
        this.listaClientes = listaClientes;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
