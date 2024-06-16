package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import com.views.av3_estrutura_de_dados.util.Constraints;
import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.IndexVendedor;

public class CadastroProdutoVendedorController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private PilhaProdutos pilhaProdutos=null;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private TextField textFieldNomeProduto, textFieldValor, textFieldQuantidade;
    @FXML
    private TextArea textFieldDescricao;
    @FXML
    private Button btnCadastrar, btnCancelar;

    @FXML
    public void onBtnCancelar(ActionEvent event) throws IOException {
        try {
            CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                    this.listaClientes, this.pilhaProdutos);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes){
        System.out.println("Chegou");
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos){
        this.pilhaProdutos = pilhaProdutos;
    }

    @Override
    public void getPilhaProdutos(){
        System.out.println("==========================");
        this.pilhaProdutos.mostrarProdutos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldSemAcento(this.textFieldNomeProduto);
        Constraints.setTextAreaDescricao(this.textFieldDescricao);
        Constraints.setTextFieldValor(this.textFieldValor);
        Constraints.setTextFieldQuantidade(this.textFieldQuantidade);
        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
