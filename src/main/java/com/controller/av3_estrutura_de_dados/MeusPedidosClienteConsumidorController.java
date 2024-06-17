package com.controller.av3_estrutura_de_dados;

import java.net.URL;
import java.util.ResourceBundle;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.application.Platform;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.TabelaProdutosAComprarModel;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import javafx.scene.control.cell.PropertyValueFactory;

public class MeusPedidosClienteConsumidorController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente;
    @FXML
    private Label labelNomeUsuario;

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
        this.arvoreComprasCliente.obterTodosPedidosCliente(this.listaClientes.usuarioLogado.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(this::setarNomeUsuarioNoLabel);
    }

    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }
}
