package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.models.av3_estrutura_de_dados.Entities.TabelaProdutosAComprarModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

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
    private TableView<TabelaMeusPedidosConsumidorModel> tabelaMeusPedidosConsumidor;
    @FXML
    private TableColumn<TabelaMeusPedidosConsumidorModel, String> nomeProdutoColumn;
    @FXML
    private TableColumn<TabelaMeusPedidosConsumidorModel, Double> valorProdutoColumn;
    @FXML
    private TableColumn<TabelaMeusPedidosConsumidorModel, String> nomeVendedorColumn;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setupTabela();

        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::popularTabela);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    private void setupTabela(){
        nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaMeusPedidosConsumidorModel,
                String>("nomeProduto"));
        valorProdutoColumn.setCellValueFactory(new PropertyValueFactory<TabelaMeusPedidosConsumidorModel,
                Double>("precoProduto"));
        nomeVendedorColumn.setCellValueFactory(new PropertyValueFactory<TabelaMeusPedidosConsumidorModel,
                String>("nomeVendedor"));
    }

    public void popularTabela(){
        this.tabelaMeusPedidosConsumidor.setItems(this.obterPedidosDaArvore());
    }

    private ObservableList<TabelaMeusPedidosConsumidorModel> obterPedidosDaArvore(){
        ObservableList<TabelaMeusPedidosConsumidorModel> produtos = FXCollections.observableArrayList();

        PilhaProdutos copiaPilhaProdutos = this.arvoreComprasCliente.obterTodosPedidosCliente(
                this.listaClientes.usuarioLogado.getId());

        int tamanhoPilha = copiaPilhaProdutos.tamanhoPilha;

        for (int i = 0; i < tamanhoPilha; i++ ){
            NoPilhaProduto produto = copiaPilhaProdutos.desempilharProduto();
            if (produto != null){
                produtos.add(new TabelaMeusPedidosConsumidorModel(produto.getNome(), produto.getPreco(),
                        this.listaClientes.obterNomeVendedor(produto.getIdClienteVendedor()),
                        produto.getIdClienteVendedor()));
            }
        }

        return produtos;
    }
}
