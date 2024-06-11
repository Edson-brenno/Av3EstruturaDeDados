package com.models.av3_estrutura_de_dados.Entities.Listas;

import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import com.models.av3_estrutura_de_dados.Entities.Listas.NosListas.NoCliente;

public class ListaClientes {

    public NoCliente inicio;

    public ListaClientes() {
        this.inicio = null;
    }

    public boolean listaVazia(){
        return this.inicio == null;
    }

    public void adicionarCliente(String nome, String email, String nomeUsuario,
                                 String senha, TipoClienteEnum tipoCliente){

        if(this.listaVazia()){
            this.inicio = new NoCliente(nome, email, nomeUsuario, senha, tipoCliente);
        }else{
            NoCliente atual = this.inicio;

            while(atual.getProximoCliente() != null){
                atual = atual.getProximoCliente();
            }

            atual.setProximoCliente(new NoCliente(nome, email, nomeUsuario, senha, tipoCliente));
            atual.getProximoCliente().setClienteAnterior(atual);
        }

    }
}
