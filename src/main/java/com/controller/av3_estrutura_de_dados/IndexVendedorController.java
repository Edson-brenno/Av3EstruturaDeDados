package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.CadastroProdutoVendedor;
import com.views.av3_estrutura_de_dados.VerProdutosVendedor;

public class IndexVendedorController implements Initializable, Controller {
    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private PilhaProdutos pilhaProdutos=null;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Button btnCadastroProduto, btnVerProdutos;

    @Override
    public void setListaClientes(ListaClientes listaClientes){
        System.out.println("Chegou");
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos){
        this.pilhaProdutos = pilhaProdutos;
    }

    public void getPilhaProdutos(){
        System.out.println("==========================");
        this.pilhaProdutos.mostrarProdutos();
    }

    @FXML
    public void onBtnCadastroProdutoOnAction(ActionEvent event) throws IOException {
        try{

            CarregarPagina.trocarPagina(event, CadastroProdutoVendedor.class,
                    "CadastroProdutoVendedor-view.fxml", this.listaClientes,
                    this.pilhaProdutos);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnVerProdutosOnAction(ActionEvent event) throws IOException {
        try {
            CarregarPagina.trocarPagina(event, VerProdutosVendedor.class,
                    "VerProdutosVendedor-view.fxml", this.listaClientes, this.pilhaProdutos);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
