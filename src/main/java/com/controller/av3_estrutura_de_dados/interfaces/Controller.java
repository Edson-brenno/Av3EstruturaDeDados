package com.controller.av3_estrutura_de_dados.interfaces;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;

public interface Controller {
    void setListaClientes(ListaClientes listaClientes);
    void setPilhaProdutos(PilhaProdutos pilhaProdutos);
}
