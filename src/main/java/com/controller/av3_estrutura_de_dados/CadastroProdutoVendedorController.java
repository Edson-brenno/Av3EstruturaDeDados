package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.Login;
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
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import com.views.av3_estrutura_de_dados.util.Constraints;
import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.IndexVendedor;

public class CadastroProdutoVendedorController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private TextField textFieldNomeProduto, textFieldValor;
    @FXML
    private TextArea textFieldDescricao;
    @FXML
    private Button btnCadastrar, btnCancelar, btnDeslogar;

    @FXML
    public void onBtnCancelar(ActionEvent event) throws IOException {
        try {
            CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnCadastrar(ActionEvent event) throws IOException {
        try {

            this.pilhaProdutos.emplilharProduto(this.textFieldNomeProduto.getText(), this.textFieldDescricao.getText(),
                    this.listaClientes.usuarioLogado.getId(),
                    null,Double.parseDouble(this.textFieldValor.getText().replace(",",".")));

            CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch(RuntimeException e){
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
    public void setArvoreComprasCliente(ArvoreComprasCliente arvore) {
        this.arvoreComprasCliente = arvore;
    }

    @Override
    public void getArvoreComprasCliente() {
        this.arvoreComprasCliente.obterTodosPedidosCliente(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldSemAcento(this.textFieldNomeProduto);
        Constraints.setTextAreaDescricao(this.textFieldDescricao);
        Constraints.setTextFieldValor(this.textFieldValor);
        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
