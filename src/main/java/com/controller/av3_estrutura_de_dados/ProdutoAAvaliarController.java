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

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.Filas.FilaAvaliacaoPedido;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.MeuPedidosClienteConsumidor;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Button buttonVoltar,buttonAvaliar;


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

        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::setarVisibilidadeComponentes);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    private void setarVisibilidadeComponentes(){
        if(this.filaAvaliacaoPedido == null){
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
