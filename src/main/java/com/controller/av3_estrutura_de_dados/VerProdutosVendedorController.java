package com.controller.av3_estrutura_de_dados;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.models.av3_estrutura_de_dados.Entities.TabelaVerProdutosVendedorModel;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;

import com.views.av3_estrutura_de_dados.Login;
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
    private ArvoreComprasCliente arvoreComprasCliente;
    @FXML
    private TableView<TabelaVerProdutosVendedorModel> tabelaVerProdutos; //Tabela
    @FXML
    private TableColumn<TabelaVerProdutosVendedorModel, String> nomeProdutoColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaVerProdutosVendedorModel, Double> precoColumn; // coluna tabela
    @FXML
    private Button btnVoltar, btnCadastroProduto, btnDeslogar;

    @FXML
    // Função a ser chamada ao clicar no botao de voltar
    public void onBtnVoltarAction(ActionEvent event) throws IOException {
        try {
            // Carrega a página IndexVendedor
            CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    // Funçao a ser chamada ao clicar no butao de cadastrar produto
    public void onBtnCadastroProdutoAction(ActionEvent event) throws IOException {
        try{
            // Carrega pagina de cadastro de produtos
            CarregarPagina.trocarPagina(event, CadastroProdutoVendedor.class,
                    "CadastroProdutoVendedor-view.fxml", this.listaClientes, this.pilhaProdutos,
                    this.arvoreComprasCliente);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao de deslogar
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            // Desloga o usuario
            this.listaClientes.deslogarCliente();
            // Carrega a pagina de login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    // Função a ser chamada na inicialização do fxml
    public void initialize(URL location, ResourceBundle resources) {
        // configura tabela
        setupTabela();

        //Funções a serem executadas após a inicializacao
        Platform.runLater(this::popularTabela);
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    //Configura tabela
    private void setupTabela() {
        // Seta os atributos no model para a tabela
        nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaVerProdutosVendedorModel, String>("nome"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<TabelaVerProdutosVendedorModel, Double>("preco"));
    }

    //Escreve os dados na tabela
    private void popularTabela(){
        tabelaVerProdutos.setItems(this.obterProdutosDaPilha());
    }

    // Obtem os produtos da prilha e transforma para o formato reconhecido pela tabela
    private ObservableList<TabelaVerProdutosVendedorModel> obterProdutosDaPilha(){

        ObservableList<TabelaVerProdutosVendedorModel> produtos = FXCollections.observableArrayList();

        //Obtém produtos do vendedor
        PilhaProdutos copiaPilhaProdutos = this.pilhaProdutos.gerarCopiaPilhaProdutosIdVendedor(
                this.listaClientes.usuarioLogado.getId());

        int tamanhoPilha = this.pilhaProdutos.tamanhoPilha;

        for (int i = 0; i < tamanhoPilha; i++ ){
            NoPilhaProduto produto = copiaPilhaProdutos.desempilharProduto();
            if (produto != null){
                // Adiciona o produto no formato ao qual é reconhecido pela tabela
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

    @Override
    public void setArvoreComprasCliente(ArvoreComprasCliente arvore) {
        this.arvoreComprasCliente = arvore;
    }

    @Override
    public void getArvoreComprasCliente() {
        this.arvoreComprasCliente.obterTodosPedidosCliente(2);
    }
}
