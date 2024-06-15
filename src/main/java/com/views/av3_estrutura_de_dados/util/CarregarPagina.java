package com.views.av3_estrutura_de_dados.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import com.controller.av3_estrutura_de_dados.util.SetarListaClientesDoController;
import com.controller.av3_estrutura_de_dados.util.SetaPilhaProdutosDoController;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import javafx.stage.Stage;
import java.io.IOException;

public class CarregarPagina {
    // Troca a pagina da aplicação
    public static void trocarPagina(ActionEvent event, Class<?> classeAplicacao, String nomeArquivoFxml,
                                    ListaClientes listaClientes, PilhaProdutos pilhaProdutos) throws IOException {
        FXMLLoader loader = new FXMLLoader(classeAplicacao.getResource(nomeArquivoFxml));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        SetarListaClientesDoController.setarListaClientes(loader, listaClientes);
        SetaPilhaProdutosDoController.setarPilhaProdutos(loader, pilhaProdutos);
        stage.setScene(scene);
        stage.show();
    }
}
