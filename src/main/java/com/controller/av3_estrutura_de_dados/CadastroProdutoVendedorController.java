package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import com.views.av3_estrutura_de_dados.util.Constraints;

public class CadastroProdutoVendedorController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private PilhaProdutos pilhaProdutos=null;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private TextField textFieldNomeProduto, textFieldDescricao, textFieldValor, textFieldQuantidade;

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
    public void getPilhaProdutos(){
        System.out.println("==========================");
        this.pilhaProdutos.mostrarProdutos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldSemAcento(this.textFieldNomeProduto);
        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
