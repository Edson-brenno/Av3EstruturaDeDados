package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.TabelaProdutosAComprarModel;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexClienteController implements Initializable, Controller{

    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private PilhaProdutos pilhaProdutos=null;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente=null;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private TableView<TabelaProdutosAComprarModel> tabelaProdutosAComprar;
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> nomeProdutoColumn;
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> descricaoProdutoColumn;
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, Double> precoProdutoColumn;
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> nomeVendedorColumn;
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> nomeComprarColumn;

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

    @Override
    public void setArvoreComprasCliente(ArvoreComprasCliente arvore) {
        this.arvoreComprasCliente = arvore;
    }

    @Override
    public void getArvoreComprasCliente() {
        this.arvoreComprasCliente.obterTodosPedidosCliente(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::popularTabela);
    }

    public void setupTable(){
        nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaProdutosAComprarModel,
                String>("nomeProduto"));
        descricaoProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaProdutosAComprarModel,
                String>("descricaoProduto"));
        precoProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaProdutosAComprarModel,
                Double>("precoProduto"));
        nomeVendedorColumn.setCellValueFactory(new PropertyValueFactory<TabelaProdutosAComprarModel,
                String>("nomeVendedor"));
        nomeComprarColumn.setCellValueFactory(new PropertyValueFactory<TabelaProdutosAComprarModel,
                String>("textoCompra"));

    }

    public void popularTabela(){
        this.tabelaProdutosAComprar.setItems(this.obterProdutosDaPilha());
    }
    private ObservableList<TabelaProdutosAComprarModel> obterProdutosDaPilha(){
        ObservableList<TabelaProdutosAComprarModel> produtos = FXCollections.observableArrayList();

        PilhaProdutos copiaPilhaProdutos = this.pilhaProdutos.gerarCopiaPilhaProdutos();

        int tamanhoPilha = this.pilhaProdutos.tamanhoPilha;

        for (int i = 0; i < tamanhoPilha; i++ ){
            NoPilhaProduto produto = copiaPilhaProdutos.desempilharProduto();
            if (produto != null){
                produtos.add(new TabelaProdutosAComprarModel(produto.getNome(), produto.getDescricao(),
                        produto.getPreco(), listaClientes.obterNomeVendedor(produto.getIdClienteVendedor()),
                        produto.getIdClienteVendedor()));
            }
        }

        return produtos;
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
