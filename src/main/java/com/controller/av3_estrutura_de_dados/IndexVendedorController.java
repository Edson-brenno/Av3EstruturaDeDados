package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.views.av3_estrutura_de_dados.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;

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
    private ArvoreComprasCliente arvoreComprasCliente=null;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Button btnCadastroProduto, btnVerProdutos, btnDeslogar;
    @FXML
    private Label labelValorTotalVendas;
    @FXML
    private Label labelTotalVendas;

    @Override
    public void setListaClientes(ListaClientes listaClientes){
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos){
        this.pilhaProdutos = pilhaProdutos;
    }

    public void getPilhaProdutos(){
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

    @FXML
    // Função a ser chamada ao clicar no botao cadastros de produto
    public void onBtnCadastroProdutoOnAction(ActionEvent event) throws IOException {
        try{
            // Carrega página de cadatro de produto do vendedor
            CarregarPagina.trocarPagina(event, CadastroProdutoVendedor.class,
                    "CadastroProdutoVendedor-view.fxml", this.listaClientes,
                    this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao de ver produto
    public void onBtnVerProdutosOnAction(ActionEvent event) throws IOException {
        try {
            // Carrega a pagina de ver produtos do vendedor
            CarregarPagina.trocarPagina(event, VerProdutosVendedor.class,
                    "VerProdutosVendedor-view.fxml", this.listaClientes, this.pilhaProdutos,
                    this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar em deslogar
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            // Seta usuario logado como nulo
            this.listaClientes.deslogarCliente();
            // Carrega página de login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    // Função a ser executada na inicialização do fxml
    public void initialize(URL url, ResourceBundle rb) {

        // Funções a serem rodas após a inicialização
        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::obterValorTotalDeVendas);
        Platform.runLater(this::obterTotalDeVendas);
    }

    // Seta o nome do usario no label
    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    // Obtém o valor Total de vendas
    public void obterValorTotalDeVendas(){
        // Se a arvore não for vazia
        if(this.arvoreComprasCliente!=null){
            // Obtém todas as vendas com o id do vendedor
            PilhaProdutos vendasProdutos = this.arvoreComprasCliente.obterTodasVendasClienteVendedor(
                    this.listaClientes.usuarioLogado.getId());

            double totalVendas = 0;
            int tamanhoPilha = vendasProdutos.tamanhoPilha;

            for(int i = 0; i < tamanhoPilha; i++){
                NoPilhaProduto produtoVendido = vendasProdutos.desempilharProduto();
                totalVendas += produtoVendido.getPreco();
            }
            // Seta valor no label
            this.labelValorTotalVendas.setText("R$ " + totalVendas);
        }else{
            this.labelValorTotalVendas.setText("R$ 0.00");
        }
    }

    // Obtem o total de venda
    public void obterTotalDeVendas(){
        // Se a arvore não for vazia
        if(this.arvoreComprasCliente!=null){
            // Obtém todas as vendas do vendedor
            PilhaProdutos vendasProdutos = this.arvoreComprasCliente.obterTodasVendasClienteVendedor(
                    this.listaClientes.usuarioLogado.getId());

            int totalProdutosVendidos = 0;
            int tamanhoPilha = vendasProdutos.tamanhoPilha;

            for(int i = 0; i < tamanhoPilha; i++){
                NoPilhaProduto produtoVendido = vendasProdutos.desempilharProduto();
                totalProdutosVendidos ++;
            }
            // Seta total no label de total de vendas
            this.labelTotalVendas.setText(String.valueOf(totalProdutosVendidos));
        }else {
            this.labelTotalVendas.setText("0");
        }
    }
}
