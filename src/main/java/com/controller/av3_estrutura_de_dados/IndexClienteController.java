package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.views.av3_estrutura_de_dados.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.TabelaProdutosAComprarModel;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.MeuPedidosClienteConsumidor;
import com.views.av3_estrutura_de_dados.ProdutosAAvaliar;

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
    private Button btnDeslogar;
    @FXML
    private TableView<TabelaProdutosAComprarModel> tabelaProdutosAComprar; // tabela
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> nomeProdutoColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> descricaoProdutoColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, Double> precoProdutoColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> nomeVendedorColumn; // coluna tabela
    @FXML
    private TableColumn<TabelaProdutosAComprarModel, String> nomeComprarColumn; // coluna tabela

    @FXML
    // Função a ser chamada ao clicar no botao de minhas compras
    public void onBtnMinhasComprasAction(ActionEvent event) throws IOException {
        try{
            // Carrega pagina meus pedidos
            CarregarPagina.trocarPagina(event, MeuPedidosClienteConsumidor.class,
                    "MeuPedidosClienteConsumidor-view.fxml", this.listaClientes, this.pilhaProdutos,
                    this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao pedidos a avaliar
    public void onBtnPedidosAAvaliarAction(ActionEvent event) throws IOException {
        try {
            // Carrega página dos pedidos a avaliar
            CarregarPagina.trocarPagina(event, ProdutosAAvaliar.class, "ProdutosAAvaliar.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao de deslogar
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            // Desloga usuário
            this.listaClientes.deslogarCliente();
            // carrega pagina login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
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
        this.arvoreComprasCliente.obterTodosPedidosCliente(2);
    }

    @Override
    // Função a ser executada na inicialização do fxml
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // configura tabela
        setupTable();

        // Add listenner nas rows da tabela
        tabelaProdutosAComprar.setRowFactory(tv ->{
            TableRow<TabelaProdutosAComprarModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> { // Evento para caso tenha um clique no row
               if(!row.isEmpty()){ // Se a row não for vazia
                   TabelaProdutosAComprarModel rowData = row.getItem(); // Obtém dados da row
                   // Adiciona compra na arvore
                   this.arvoreComprasCliente.adicionarCompraCliente(rowData.getNomeProduto(), rowData.getPrecoProduto(),
                           rowData.getIdVendedor(), this.listaClientes.usuarioLogado.getId());

                   // Alerta de compraRealizada
                   Alert compraRealizada = new Alert(Alert.AlertType.INFORMATION);
                   compraRealizada.setTitle("Compra Realizada");
                   compraRealizada.setHeaderText(null);
                   compraRealizada.setContentText("A compra do produto: " + rowData.getNomeProduto() +
                           " foi realizada com sucesso!");
                   compraRealizada.showAndWait();
               }
            });
            return row;
        });

        // Muda cor da coluna com o nome comprar
        nomeComprarColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #34b1eb; -fx-text-fill: white;" +
                "-fx-border-color: black;");

        // Funções a serem executadas após a inicalizacao
        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::popularTabela);
    }

    // Configura tabela
    public void setupTable(){
        // Configura cada coluna a um atributo
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

    // escreve dados na tabela
    public void popularTabela(){
        this.tabelaProdutosAComprar.setItems(this.obterProdutosDaPilha());
    }

    // Obtem os produtos da pilha de produtos
    private ObservableList<TabelaProdutosAComprarModel> obterProdutosDaPilha(){
        ObservableList<TabelaProdutosAComprarModel> produtos = FXCollections.observableArrayList();

        // Obtém todos produtos cadastrados
        PilhaProdutos copiaPilhaProdutos = this.pilhaProdutos.gerarCopiaPilhaProdutos();

        if(copiaPilhaProdutos != null){ // Se existir produto

            int tamanhoPilha = this.pilhaProdutos.tamanhoPilha;

            for (int i = 0; i < tamanhoPilha; i++ ){
                // desenpilha produto
                NoPilhaProduto produto = copiaPilhaProdutos.desempilharProduto();
                if (produto != null){
                    // Adiciona o produto em um formato reconhecido pela tabela
                    produtos.add(new TabelaProdutosAComprarModel(produto.getNome(), produto.getDescricao(),
                            produto.getPreco(), listaClientes.obterNomeVendedor(produto.getIdClienteVendedor()),
                            produto.getIdClienteVendedor()));
                }
            }
        }

       return produtos;
    }

    // Função para setar nome do usuario
    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
