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

import java.io.IOException;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Estância lista de clientes
            ListaClientes listaCliente = new ListaClientes();
            // Estância pilhaProdutos
            PilhaProdutos pilhaProdutos = new PilhaProdutos();
            // Estância arvoreComprasCliente
            ArvoreComprasCliente arvoreComprasCliente = new ArvoreComprasCliente();

            // Carrega o Fxml
            FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login-view.fxml"));
            Scene scene = new Scene(loader.load());
            // Obtem o controller do login
            LoginController controller = (LoginController) loader.getController();
            // Seta as estâncias da lista, pilha e arvore no controller para que se mudar de página possa ir para outra
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
