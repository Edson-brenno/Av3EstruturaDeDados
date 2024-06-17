package com.controller.av3_estrutura_de_dados.util;


import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import javafx.fxml.FXMLLoader;

public class SetarArvoreComprasDoController {
    public static void setarArvoreCompraProdutos(FXMLLoader loader, ArvoreComprasCliente arvoreComprasCliente){
        Controller controller = loader.getController();
        controller.setArvoreComprasCliente(arvoreComprasCliente);
        controller.getArvoreComprasCliente();
    }
}
