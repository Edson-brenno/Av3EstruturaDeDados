package com.models.av3_estrutura_de_dados.Entities.Pilhas;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;


public class PilhaProdutos {
    public NoPilhaProduto base;
    public NoPilhaProduto topo;

    public PilhaProdutos() {
        this.base = this.topo = null;
    }

    public Boolean pilhaEstaVazia(){
        return  this.base == null && this.topo == null;
    }

    public void emplilharProduto(String nome, String descricao, double preco, int quantidade){
        if(pilhaEstaVazia()){
            this.base = this.topo = new NoPilhaProduto(nome, descricao, preco, quantidade);
        }else{
            this.topo.setProximoNo(new NoPilhaProduto(nome, descricao, preco, quantidade));
            this.topo = this.topo.getProximoNo();
        }
    }

    public NoPilhaProduto desempilharProduto(){
        if(pilhaEstaVazia()){
            return null;
        }else{
            NoPilhaProduto ponteiro = this.base;
            while(ponteiro.getProximoNo() != this.topo){
                ponteiro = ponteiro.getProximoNo();
            }
            this.topo = ponteiro;
            this.topo.setProximoNo(null);
            return ponteiro.getProximoNo();
        }
    }

    public void mostrarProdutos(){
        if(!pilhaEstaVazia()){
            NoPilhaProduto ponteiro = this.base;
            while(ponteiro.getProximoNo() != this.topo) {
                System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                        " - " + ponteiro.getQuantidade());
                ponteiro = ponteiro.getProximoNo();
            }
            System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                    " - " + ponteiro.getQuantidade());

            System.out.println(this.topo.getNome() + " - " + this.topo.getDescricao() + " - " + this.topo.getPreco() +
                    " - " + this.topo.getQuantidade() );
        }
    }

}
