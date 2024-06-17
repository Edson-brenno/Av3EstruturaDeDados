package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.Button;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.TabelaProdutosAComprarModel;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.MeuPedidosClienteConsumidor;

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
    private Button btnMinhasCompras;
    @FXML
    private Button btnPedidosAAvaliar;
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

    @FXML
    public void onBtnMinhasComprasAction(ActionEvent event) throws IOException {
        try{
            CarregarPagina.trocarPagina(event, MeuPedidosClienteConsumidor.class,
                    "MeuPedidosClienteConsumidor-view.fxml", this.listaClientes, this.pilhaProdutos,
                    this.arvoreComprasCliente);
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
        this.arvoreComprasCliente.obterTodosPedidosCliente(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        tabelaProdutosAComprar.setRowFactory(tv ->{
            TableRow<TabelaProdutosAComprarModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
               if(!row.isEmpty()){
                   TabelaProdutosAComprarModel rowData = row.getItem();
                   this.arvoreComprasCliente.adicionarCompraCliente(rowData.getNomeProduto(), rowData.getPrecoProduto(),
                           rowData.getIdVendedor(), this.listaClientes.usuarioLogado.getId());
               }
            });
            return row;
        });

        nomeComprarColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #34b1eb; -fx-text-fill: white;" +
                "-fx-border-color: black;");
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
