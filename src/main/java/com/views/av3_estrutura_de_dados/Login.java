package com.views.av3_estrutura_de_dados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.controller.av3_estrutura_de_dados.LoginController;
import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;

import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;

import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;

import java.io.IOException;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            ListaClientes listaCliente = new ListaClientes();
            listaCliente.adicionarCliente("Edson Brenno", "teste@gmail.com", "123456",
                    TipoClienteEnum.VENDEDOR);
            listaCliente.adicionarCliente("Cliente", "cliente@gmail.com", "123456",
                    TipoClienteEnum.CONSUMIDOR);
            PilhaProdutos pilhaProdutos = new PilhaProdutos();

            ArvoreComprasCliente arvoreComprasCliente = new ArvoreComprasCliente();

            FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login-view.fxml"));
            Scene scene = new Scene(loader.load());
            LoginController controller = (LoginController) loader.getController();
            controller.setListaClientes(listaCliente);
            controller.setPilhaProdutos(pilhaProdutos);
            controller.setArvoreComprasCliente(arvoreComprasCliente);
            stage.setTitle("Os mano");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
