package com.views.av3_estrutura_de_dados;

import com.models.av3_estrutura_de_dados.Entities.Listas.NosListas.NoCliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.controller.av3_estrutura_de_dados.LoginController;
import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;

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
            PilhaProdutos pilhaProdutos = new PilhaProdutos();
            NoPilhaProduto t1;
            pilhaProdutos.emplilharProduto("p1", "p1", 1L,10.65, 5);
            pilhaProdutos.emplilharProduto("p2", "p2", 1L,14.65, 10);
            pilhaProdutos.emplilharProduto("p3", "p3", 1L,15.65, 15);
            pilhaProdutos.emplilharProduto("p4", "p4", 1L,16.65, 20);
            t1 = pilhaProdutos.desempilharProduto();
            t1 = pilhaProdutos.desempilharProduto();
            t1 = pilhaProdutos.desempilharProduto();
            t1 = pilhaProdutos.desempilharProduto();
            FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login-view.fxml"));
            Scene scene = new Scene(loader.load());
            LoginController controller = (LoginController) loader.getController();
            controller.setListaClientes(listaCliente);
            controller.setPilhaProdutos(pilhaProdutos);
            stage.setTitle("Os mano");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
