package com.controller.av3_estrutura_de_dados.util;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import javafx.fxml.FXMLLoader;

public class SetarListaClientesDoController {
    public static void setarListaClientes(FXMLLoader loader, ListaClientes listaClientes){
        Controller controller = loader.getController();
        controller.setListaClientes(listaClientes);
    }
}
