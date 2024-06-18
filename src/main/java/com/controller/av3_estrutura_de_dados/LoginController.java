package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import com.views.av3_estrutura_de_dados.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import com.views.av3_estrutura_de_dados.Cadastro;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;
import com.views.av3_estrutura_de_dados.IndexVendedor;
import com.views.av3_estrutura_de_dados.IndexCliente;

import javafx.application.Platform;

public class LoginController implements Initializable, Controller {
    @FXML
    private ListaClientes listaClientes=null;

    @FXML
    private PilhaProdutos pilhaProdutos=null;

    @FXML
    private ArvoreComprasCliente arvoreComprasCliente=null;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label mensagemError;

    @FXML
    private Button botaoCadastro, btnLogin;

    @FXML
    // Função chamada ao clicarem no botao de login, realiza o login e redireciona para a página correta
    public void onBtnLoginClickAction(ActionEvent event) throws IOException {
        try {
            // Se o usuário existe
            if(this.oLoginEValido()){
                // se o cliente for do tipo Vendedor
                if(this.listaClientes.usuarioLogado.getTipoCliente() == TipoClienteEnum.VENDEDOR){
                    // Carrega a página index do vendedor
                    CarregarPagina.trocarPagina(event, IndexVendedor.class,
                            "IndexVendedor-view.fxml", this.listaClientes, this.pilhaProdutos,
                            this.arvoreComprasCliente);
                }else{
                    //Carrega a pagina index do cliente
                    CarregarPagina.trocarPagina(event, IndexCliente.class,
                            "IndexCliente-view.fxml", this.listaClientes, this.pilhaProdutos,
                            this.arvoreComprasCliente);
                }
            }
            else{
                // Alerta do error no login
                Alert errorLogin = new Alert(Alert.AlertType.ERROR);
                errorLogin.setTitle("Error no login");
                errorLogin.setHeaderText(null);
                errorLogin.setContentText("Usuario ou senha incorretos");
                errorLogin.showAndWait();
            }

        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    // Função chamada ao clicarem no botão de cadastro
    public void onBtCadastroClickAction(ActionEvent event) throws IOException {
        try {
            // Carrega página de cadastro
            CarregarPagina.trocarPagina(event, Cadastro.class, "Cadastro-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
//
        }catch (RuntimeException e){
            e.getStackTrace();
        }
    }

    // Verifica se o login e a senha passado são válidos
    public boolean oLoginEValido(){
        return this.listaClientes != null && this.listaClientes.LogarCliente(this.emailTextField.getText(),
                this.passwordTextField.getText());
    }

    @Override
    public void setListaClientes(ListaClientes listaClientes) {
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

    @Override
    // Função a ser rodada na inicialização do fxml
    public void initialize(URL url, ResourceBundle rb) {
        // Adiciona listener no campo de email
        Constraints.setTextFieldEmail(this.emailTextField);

    }

}
