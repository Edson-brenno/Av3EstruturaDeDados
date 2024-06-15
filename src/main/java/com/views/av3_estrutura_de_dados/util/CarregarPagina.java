package com.views.av3_estrutura_de_dados.util;

import com.views.av3_estrutura_de_dados.Login;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import com.controller.av3_estrutura_de_dados.util.SetarListaClientesDoController;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import javafx.stage.Stage;
import java.io.IOException;

public class CarregarPagina {
    // Troca a pagina da aplicação
    public static void trocarPagina(ActionEvent event, Class<?> classeAplicacao, String nomeArquivoFxml, ListaClientes listaClientes) throws IOException {
        FXMLLoader loader = new FXMLLoader(classeAplicacao.getResource(nomeArquivoFxml));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        SetarListaClientesDoController.setarListaClientes(loader, listaClientes);
        stage.setScene(scene);
        stage.show();
    }
}