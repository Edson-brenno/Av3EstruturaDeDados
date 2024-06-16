package com.controller.av3_estrutura_de_dados;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.models.av3_estrutura_de_dados.Entities.TabelaVerProdutosVendedorModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class VerProdutosVendedorController implements Initializable, Controller{

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;
    @FXML
    private TableView<TabelaVerProdutosVendedorModel> tabelaVerProdutos;
    @FXML
    private TableColumn<TabelaVerProdutosVendedorModel, String> nomeProdutoColumn;
    @FXML
    private TableColumn<TabelaVerProdutosVendedorModel, Double> precoColumn;
    @FXML
    private TableColumn<TabelaVerProdutosVendedorModel, Integer> quantidadeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::popularTabela);
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    private void popularTabela(){
        tabelaVerProdutos.setItems(this.obterProdutosDaPilha());
    }

    private ObservableList<TabelaVerProdutosVendedorModel> obterProdutosDaPilha(){
        ObservableList<TabelaVerProdutosVendedorModel> produtos = FXCollections.observableArrayList();

        for (int i = 1; i < this.pilhaProdutos.tamanhoPilha; i++ ){
            NoPilhaProduto produto = this.pilhaProdutos.desempilharProduto();

            if (produto != null){
                produtos.add(new TabelaVerProdutosVendedorModel(produto.getNome(), produto.getPreco(),
                        produto.getQuantidade()));
            }
        }

        return produtos;
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
