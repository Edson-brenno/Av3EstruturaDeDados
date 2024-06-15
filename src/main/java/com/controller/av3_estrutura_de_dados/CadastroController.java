package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.util.Constraints;
import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import com.views.av3_estrutura_de_dados.Login;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
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
    public void onBtnVoltarClickAction(ActionEvent event) throws IOException {
        try{
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml", this.listaClientes,
                    this.pilhaProdutos);
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnCadastrarClickAction(ActionEvent event) throws IOException {
        try{
            if (this.nomeCompleto.getLength() > 0 &&
            this.email.getLength() > 0 && (this.comboTipoCliente.getValue() == TipoClienteEnum.CONSUMIDOR ||
                    this.comboTipoCliente.getValue() == TipoClienteEnum.VENDEDOR)){

                this.listaClientes.adicionarCliente(this.nomeCompleto.getText(), this.email.getText(),
                        this.senha.getText(),
                        this.comboTipoCliente.getValue());

                CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml", this.listaClientes,
                        this.pilhaProdutos);

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.comboTipoCliente.getItems().addAll(TipoClienteEnum.values());

      Constraints.setTextFieldNomeCompleto(this.nomeCompleto);
      Constraints.setTextFieldEmail(this.email);
    }
}
