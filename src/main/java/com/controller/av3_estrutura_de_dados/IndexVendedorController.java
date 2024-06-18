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

    @Override
    public void setArvoreComprasCliente(ArvoreComprasCliente arvore) {
        this.arvoreComprasCliente = arvore;
    }

    @Override
    public void getArvoreComprasCliente() {
        this.arvoreComprasCliente.obterTodosPedidosCliente(2);
    }

    @FXML
    public void onBtnCadastroProdutoOnAction(ActionEvent event) throws IOException {
        try{

            CarregarPagina.trocarPagina(event, CadastroProdutoVendedor.class,
                    "CadastroProdutoVendedor-view.fxml", this.listaClientes,
                    this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnVerProdutosOnAction(ActionEvent event) throws IOException {
        try {
            CarregarPagina.trocarPagina(event, VerProdutosVendedor.class,
                    "VerProdutosVendedor-view.fxml", this.listaClientes, this.pilhaProdutos,
                    this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            this.listaClientes.deslogarCliente();
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::obterValorTotalDeVendas);
        Platform.runLater(this::obterTotalDeVendas);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    public void obterValorTotalDeVendas(){
        if(this.arvoreComprasCliente!=null){
            PilhaProdutos vendasProdutos = this.arvoreComprasCliente.obterTodasVendasClienteVendedor(
                    this.listaClientes.usuarioLogado.getId());

            double totalVendas = 0;
            int tamanhoPilha = vendasProdutos.tamanhoPilha;

            for(int i = 0; i < tamanhoPilha; i++){
                NoPilhaProduto produtoVendido = vendasProdutos.desempilharProduto();
                totalVendas += produtoVendido.getPreco();
            }

            this.labelValorTotalVendas.setText("R$ " + totalVendas);
        }else{
            this.labelValorTotalVendas.setText("R$ 0.00");
        }
    }

    public void obterTotalDeVendas(){
        if(this.arvoreComprasCliente!=null){
            PilhaProdutos vendasProdutos = this.arvoreComprasCliente.obterTodasVendasClienteVendedor(
                    this.listaClientes.usuarioLogado.getId());

            this.labelTotalVendas.setText(String.valueOf(vendasProdutos.tamanhoPilha));
        }else {
            this.labelTotalVendas.setText("0");
        }
    }
}
