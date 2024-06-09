package com.views.av3_estrutura_de_dados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Cadastro extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(Cadastro.class.getResource("Cadastro-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("cadastro");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.getStackTrace();
        }
    }
}
