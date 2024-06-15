package com.controller.av3_estrutura_de_dados.util;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import javafx.fxml.FXMLLoader;

public class SetaPilhaProdutosDoController {
    public static void setarPilhaProdutos(FXMLLoader loader, PilhaProdutos pilhaProdutos){
        Controller controller = loader.getController();
        controller.setPilhaProdutos(pilhaProdutos);
        controller.getPilhaProdutos();
    }
}
