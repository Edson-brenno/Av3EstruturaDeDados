package com.views.av3_estrutura_de_dados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexVendedor extends Application{
    public static void main(String[] args) throws IOException{
        launch(args);
    }

    public void start(Stage stage) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(IndexVendedor.class.getResource("IndexVendedor-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
