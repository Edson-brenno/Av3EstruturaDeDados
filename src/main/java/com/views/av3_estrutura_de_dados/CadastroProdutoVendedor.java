package com.views.av3_estrutura_de_dados;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroProdutoVendedor extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(CadastroProdutoVendedor.class.getResource(
                    "CadastroProdutoVendedor-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }
}
