package com.controller.av3_estrutura_de_dados;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class VerProdutosVendedorController implements Initializable, Controller{

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos) {
        this.pilhaProdutos = pilhaProdutos;
    }

    @Override
    public void getPilhaProdutos() {
        this.pilhaProdutos.mostrarProdutos();
    }
}
