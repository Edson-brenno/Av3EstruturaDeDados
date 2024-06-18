package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.models.av3_estrutura_de_dados.Entities.TabelaProdutosAComprarModel;
import com.views.av3_estrutura_de_dados.Login;
import com.views.av3_estrutura_de_dados.ProdutosAAvaliar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import com.views.av3_estrutura_de_dados.IndexCliente;
import com.views.av3_estrutura_de_dados.util.CarregarPagina;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.TabelaMeusPedidosConsumidorModel;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import javafx.scene.control.cell.PropertyValueFactory;

public class MeusPedidosClienteConsumidorController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Button btnVoltar, btnDeslogar, btnProdutosAAvaliar;
    @FXML
    private TableView<TabelaMeusPedidosConsumidorModel> tabelaMeusPedidosConsumidor; // Tabela
    @FXML
    private TableColumn<TabelaMeusPedidosConsumidorModel, String> nomeProdutoColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaMeusPedidosConsumidorModel, Double> valorProdutoColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaMeusPedidosConsumidorModel, String> nomeVendedorColumn; // coluna tabela

    @FXML
    // Função a ser chamda ao clicar no botão de voltar
    public void onBtnVoltarAction(ActionEvent event) throws IOException {
        try {
            // Carrega index do cliente
            CarregarPagina.trocarPagina(event, IndexCliente.class, "IndexCliente-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamda ao clicar no botao de deslogar
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            // Desloga usuário
            this.listaClientes.deslogarCliente();
            // Carrega página de login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao de produtos a avaliar
    public void onBtnProdutosAAvaliar(ActionEvent event) throws IOException {
        try {
            // Carrega página de produtos a avaliar
            CarregarPagina.trocarPagina(event, ProdutosAAvaliar.class, "ProdutosAAvaliar.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
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

    @Override
    public void setArvoreComprasCliente(ArvoreComprasCliente arvore) {
        this.arvoreComprasCliente = arvore;
    }

    @Override
    public void getArvoreComprasCliente() {
        this.arvoreComprasCliente.obterTodosPedidosCliente(this.listaClientes.usuarioLogado.getId());
    }

    @Override
    // Função a ser executa na inicializacao do fxml
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura tabela
        this.setupTabela();

        // Funções a serem rodas após a inicialização
        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::popularTabela);
    }

    // Seta nome usuario na view
    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    // Configura tabela
    private void setupTabela(){
        // Seta respectivos atributos a serem buscados
        nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaMeusPedidosConsumidorModel,
                String>("nomeProduto"));
        valorProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaMeusPedidosConsumidorModel,
                Double>("precoProduto"));
        nomeVendedorColumn.setCellValueFactory(new PropertyValueFactory<TabelaMeusPedidosConsumidorModel,
                String>("nomeVendedor"));
    }

    // Escreve os dados na tabela
    public void popularTabela(){
        this.tabelaMeusPedidosConsumidor.setItems(this.obterPedidosDaArvore());
    }

    // Obtem os pedidos do cliente
    private ObservableList<TabelaMeusPedidosConsumidorModel> obterPedidosDaArvore(){
        ObservableList<TabelaMeusPedidosConsumidorModel> produtos = FXCollections.observableArrayList();

        // Obtém todos os pedidos do cliente na arvore
        PilhaProdutos copiaPilhaProdutos = this.arvoreComprasCliente.obterTodosPedidosCliente(
                this.listaClientes.usuarioLogado.getId());

        int tamanhoPilha = copiaPilhaProdutos.tamanhoPilha;

        for (int i = 0; i < tamanhoPilha; i++ ){
            NoPilhaProduto produto = copiaPilhaProdutos.desempilharProduto();
            if (produto != null){
                // Prepara dado para ser mostrado na tabela
                produtos.add(new TabelaMeusPedidosConsumidorModel(produto.getNome(), produto.getPreco(),
                        this.listaClientes.obterNomeVendedor(produto.getIdClienteVendedor()),
                        produto.getIdClienteVendedor()));
            }
        }

        return produtos;
    }
}
