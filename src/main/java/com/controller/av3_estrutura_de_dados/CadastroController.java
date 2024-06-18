package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.util.Constraints;
import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import com.views.av3_estrutura_de_dados.Login;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.views.av3_estrutura_de_dados.util.CarregarPagina;

public class CadastroController implements Initializable, Controller {

    @FXML
    private TextField nomeCompleto, email,senha;
    @FXML
    private Button btnVoltar, btnCadastrar;
    @FXML
    private ComboBox<TipoClienteEnum> comboTipoCliente;
    @FXML
    private ListaClientes listaClientes=null;
    @FXML
    private PilhaProdutos pilhaProdutos=null;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente=null; // Arvore de

    @FXML
    // Função chamada ao clicarem no botão de voltar, voltando para a tela de login
    public void onBtnVoltarClickAction(ActionEvent event) throws IOException {
        try{
            // Volta para a página de login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml", this.listaClientes,
                    this.pilhaProdutos, this.arvoreComprasCliente);
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função chamada ao clicarem no botão cadastrar, para realizar o cadastro do novo cliente
    public void onBtnCadastrarClickAction(ActionEvent event) throws IOException {
        try{
            if (this.osCamposDoCadastroSaoValidos()){ // Se is campos são válidos
                // Adiciona novo cliente na lista
                this.listaClientes.adicionarCliente(this.nomeCompleto.getText(), this.email.getText(),
                        this.senha.getText(),
                        this.comboTipoCliente.getValue());

                // Alerta informando que o cadastro foi um sucesso
                Alert registroRealizadoComSucesso = new Alert(Alert.AlertType.INFORMATION);
                registroRealizadoComSucesso.setTitle("Registrado");
                registroRealizadoComSucesso.setHeaderText(null);
                registroRealizadoComSucesso.setContentText("Registro Realizado com Sucesso");
                registroRealizadoComSucesso.showAndWait();
                // Troca de página
                CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml", this.listaClientes,
                        this.pilhaProdutos, this.arvoreComprasCliente);
            }else{
                // Alerta do tipo error informando para revisar os campos
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro");
                erro.setHeaderText(null);
                erro.setContentText("Por favor, preencha os campos corretamente");
                erro.showAndWait();
            }
        }
        catch(RuntimeException e){
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
        // Seta as opções do tipo cliente no combo box
        this.comboTipoCliente.getItems().addAll(TipoClienteEnum.values());

        // Listeners para dos campos
        Constraints.setTextFieldNomeCompleto(this.nomeCompleto);
        Constraints.setTextFieldEmail(this.email);
    }

    // Valida se os campos estão preenchidos corretamente
    private boolean osCamposDoCadastroSaoValidos(){
        return this.email.getText().matches(
                "^(?=[a-zA-Z0-9@._%+-]{6,254}$)([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") &&
                this.nomeCompleto.getText().matches("^[A-Za-z ]*$") &&
                (this.comboTipoCliente.getValue() == TipoClienteEnum.CONSUMIDOR ||
                this.comboTipoCliente.getValue() == TipoClienteEnum.VENDEDOR) &&
                this.senha.getText().length() > 3;
    }
}
