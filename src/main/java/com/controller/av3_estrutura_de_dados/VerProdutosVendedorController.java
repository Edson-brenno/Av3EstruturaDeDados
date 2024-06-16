package com.controller.av3_estrutura_de_dados;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.models.av3_estrutura_de_dados.Entities.TabelaVerProdutosVendedorModel;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.IndexVendedor;
import com.views.av3_estrutura_de_dados.CadastroProdutoVendedor;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

import java.io.IOException;
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
    private Button btnVoltar, btnCadastroProduto;

    @FXML
    public void onBtnVoltarAction(ActionEvent event) throws IOException {
        try {
            CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                    this.listaClientes, this.pilhaProdutos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBtnCadastroProdutoAction(ActionEvent event) throws IOException {
        try{
            CarregarPagina.trocarPagina(event, CadastroProdutoVendedor.class,
                    "CadastroProdutoVendedor-view.fxml", this.listaClientes, this.pilhaProdutos);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTabela();
        Platform.runLater(this::popularTabela);
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    private void setupTabela() {
        nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaVerProdutosVendedorModel, String>("nome"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<TabelaVerProdutosVendedorModel, Double>("preco"));
    }

    private void popularTabela(){
        tabelaVerProdutos.setItems(this.obterProdutosDaPilha());
    }

    private ObservableList<TabelaVerProdutosVendedorModel> obterProdutosDaPilha(){
        ObservableList<TabelaVerProdutosVendedorModel> produtos = FXCollections.observableArrayList();

        PilhaProdutos copiaPilhaProdutos = this.pilhaProdutos.gerarCopiaPilhaProdutos();
        int tamanhoPilha = this.pilhaProdutos.tamanhoPilha;

        for (int i = 0; i < tamanhoPilha; i++ ){
            NoPilhaProduto produto = copiaPilhaProdutos.desempilharProduto();
            if (produto != null){
                produtos.add(new TabelaVerProdutosVendedorModel(produto.getNome(), produto.getPreco()));
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
