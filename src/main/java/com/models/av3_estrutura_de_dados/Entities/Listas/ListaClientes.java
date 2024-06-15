package com.models.av3_estrutura_de_dados.Entities.Listas;

import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import com.models.av3_estrutura_de_dados.Entities.Listas.NosListas.NoCliente;

public class ListaClientes {

    public NoCliente inicio;
    public NoCliente usuarioLogado;

    public ListaClientes() {
        this.inicio = this.usuarioLogado = null;
    }

    public boolean listaVazia(){
        return this.inicio == null;
    }

    public void adicionarCliente(String nome, String email,
                                 String senha, TipoClienteEnum tipoCliente){

        if(this.listaVazia()){
            this.inicio = new NoCliente(nome, email, senha, tipoCliente);
        }else{
            NoCliente atual = this.inicio;

            while(atual.getProximoCliente() != null){
                atual = atual.getProximoCliente();
            }

            atual.setProximoCliente(new NoCliente(nome, email, senha, tipoCliente));
            atual.getProximoCliente().setClienteAnterior(atual);
        }

    }

    public void MostrarClientes(){

        if(!this.listaVazia()){
            NoCliente atual = this.inicio;

            while(atual.getProximoCliente() != null){
                System.out.println(atual.getNomeCompleto() + " - " + atual.getEmail() + " - " +
                        atual.getSenha() + " - " + atual.getTipoCliente());
                atual = atual.getProximoCliente();
            }

            System.out.println(atual.getNomeCompleto() + " - " + atual.getEmail() + " - " +
                    atual.getSenha() + " - " + atual.getTipoCliente());
        }

    }

    // Método resposável pelo login do cliente
    public Boolean LogarCliente(String email, String senha){
        if(this.listaVazia()) {
            return null;
        }else{
            NoCliente atual = this.inicio; // Ponteiro

            while(atual.getProximoCliente() != null){
                if(atual.getEmail().equals(email) && atual.getSenha().equals(senha)){
                    this.usuarioLogado = atual;
                    return true;
                }

                atual = atual.getProximoCliente();
            }

            if(atual.getEmail().equals(email) && atual.getSenha().equals(senha)){
                this.usuarioLogado = atual;
                return true;
            }else {
                // Retorna nulo se o usuário não for encontrado
                return null;
            }
        }
    }
}
