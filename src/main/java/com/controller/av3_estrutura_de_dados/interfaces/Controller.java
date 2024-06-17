package com.controller.av3_estrutura_de_dados.interfaces;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;

public interface Controller {
    void setListaClientes(ListaClientes listaClientes);
    void setPilhaProdutos(PilhaProdutos pilhaProdutos);
    void getPilhaProdutos();
    void setArvoreComprasCliente(ArvoreComprasCliente arvore);
    void getArvoreComprasCliente();
}
