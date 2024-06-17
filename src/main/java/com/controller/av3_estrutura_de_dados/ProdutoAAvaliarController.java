package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import com.views.av3_estrutura_de_dados.IndexCliente;
import com.views.av3_estrutura_de_dados.Login;
import com.views.av3_estrutura_de_dados.ProdutosAAvaliar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.Filas.FilaAvaliacaoPedido;
import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.MeuPedidosClienteConsumidor;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.views.av3_estrutura_de_dados.util.Constraints.setTextFlieldNotaAvaliacao;

public class ProdutoAAvaliarController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente;
    @FXML
    private FilaAvaliacaoPedido filaAvaliacaoPedido;
    @FXML
    private NoFilaAvaliacaoPedido produtoASerAvaliado;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Label mensagemSemProdutoParaAvaliar;
    @FXML
    private Label labelProduto;
    @FXML
    private Label nomeProduto;
    @FXML
    private Label descricaoAvaliacao;
    @FXML
    private TextArea textAreaDescricao;
    @FXML
    private Label labelNotaProduto;
    @FXML
    private TextField textFieldNotaProduto;
    @FXML
    private Button buttonVoltar,buttonAvaliar, btnDeslogar;

    @FXML
    public void onBtnAvaliarAction(ActionEvent event) throws IOException {
        if(this.produtoASerAvaliado != null){
            this.arvoreComprasCliente.setarPedidoAvaliado(this.produtoASerAvaliado.getIdCompra());
        }
        CarregarPagina.trocarPagina(event, ProdutosAAvaliar.class, "ProdutosAAvaliar.fxml",
                this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
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

    @FXML
    public void onBtnVoltarAction(ActionEvent event) throws IOException {
        CarregarPagina.trocarPagina(event, IndexCliente.class, "IndexCliente-view.fxml",
                this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
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
        this.arvoreComprasCliente.obterTodosPedidosCliente(2L);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextFlieldNotaAvaliacao(this.textFieldNotaProduto);
        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::setFilaAvaliacaoPedido);
        Platform.runLater(this::getProdutoASerAvaliado);
        Platform.runLater(this::setarVisibilidadeComponentes);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    private void setFilaAvaliacaoPedido(){
        this.filaAvaliacaoPedido = this.arvoreComprasCliente.obterPedidosASeremAvaliadosClienteConsumidor(
                this.listaClientes.usuarioLogado.getId(), this.listaClientes
        );
    }

    private void getProdutoASerAvaliado(){
        if(this.filaAvaliacaoPedido != null){

            this.produtoASerAvaliado = this.filaAvaliacaoPedido.desenfileiraPedidoAvaliar();
            if(this.produtoASerAvaliado == null){
                this.filaAvaliacaoPedido = null;
            }else{
                this.nomeProduto.setText(this.produtoASerAvaliado.getNomeProduto());
            }

        }
    }
    private void setarVisibilidadeComponentes(){
        if(this.produtoASerAvaliado == null){
            this.labelProduto.setVisible(false);
            this.nomeProduto.setVisible(false);
            this.descricaoAvaliacao.setVisible(false);
            this.textAreaDescricao.setVisible(false);
            this.labelNotaProduto.setVisible(false);
            this.textFieldNotaProduto.setVisible(false);
            this.buttonAvaliar.setVisible(false);
        }else{
            this.mensagemSemProdutoParaAvaliar.setVisible(false);
        }
    }
}
