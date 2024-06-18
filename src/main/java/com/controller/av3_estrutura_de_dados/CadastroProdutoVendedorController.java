package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.Login;
import com.views.av3_estrutura_de_dados.VerProdutosVendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;

import javafx.scene.control.*;

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
    private Button btnCadastrar, btnCancelar, btnDeslogar, btnVerProdutos;

    @FXML
    // Função a ser chamada ao clicar no botao cancelar
    public void onBtnCancelar(ActionEvent event) throws IOException {
        try {
            // Carrega a página da index do vendedor
            CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botão de cadastrar
    public void onBtnCadastrar(ActionEvent event) throws IOException {
        try {
            if(this.osCamposDeCadastroDoProdutoSaoValidos()){
                // Empilha novo produto
                this.pilhaProdutos.emplilharProduto(this.textFieldNomeProduto.getText(), this.textFieldDescricao.getText(),
                        this.listaClientes.usuarioLogado.getId(),
                        null,Double.parseDouble(this.textFieldValor.getText().replace(",",".")));

                // Mensagem de sucesso
                Alert cadastroRealizado = new Alert(Alert.AlertType.INFORMATION);
                cadastroRealizado.setTitle("Cadastro Realizado");
                cadastroRealizado.setHeaderText(null);
                cadastroRealizado.setContentText("O produto " + this.textFieldNomeProduto.getText() +
                        " foi cadastrado com sucesso!");
                cadastroRealizado.showAndWait();

                // Carrega página index do vendedor
                CarregarPagina.trocarPagina(event, IndexVendedor.class, "IndexVendedor-view.fxml",
                        this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
            }else{
                // Mensagem de error
                Alert errorCadastro = new Alert(Alert.AlertType.ERROR);
                errorCadastro.setTitle("Error ao cadastrar produto");
                errorCadastro.setHeaderText(null);
                errorCadastro.setContentText("Preencha os campos corretamente!");
                errorCadastro.showAndWait();
            }

        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao de delogar
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            // Desloga usuário
            this.listaClientes.deslogarCliente();
            // Carrega página login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao de ver produtos
    public void onBtnVerProdutos(ActionEvent event) throws IOException {
        // Carrega página da lista dos produtos
        CarregarPagina.trocarPagina(event, VerProdutosVendedor.class, "VerProdutosVendedor-view.fxml",
                this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes){
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos){
        this.pilhaProdutos = pilhaProdutos;
    }

    @Override
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

    @Override
    // Função a ser executada no carregamento fxml
    public void initialize(URL url, ResourceBundle rb) {
        // Seta listenner
        Constraints.setTextFieldSemAcento(this.textFieldNomeProduto);
        Constraints.setTextAreaDescricao(this.textFieldDescricao);
        Constraints.setTextFieldValor(this.textFieldValor);

        // Funções a serem rodadas após o carregamento
        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    // Seta o nome do usuario na view
    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    // Verifica se os campos de cadatros foram preenchidos corretamente
    private boolean osCamposDeCadastroDoProdutoSaoValidos(){
        return this.textFieldNomeProduto.getText().matches("^[a-zA-Z0-9 _.-]*$") &&
                this.textFieldDescricao.getText().length() <= 2500 &&
                this.textFieldValor.getText().matches("^[0-9,]+$");
    }
}
