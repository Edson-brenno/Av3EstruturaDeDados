package com.models.av3_estrutura_de_dados.Entities.Pilhas;

import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;


public class PilhaProdutos {
    public NoPilhaProduto base;
    public NoPilhaProduto topo;
    public int tamanhoPilha;

    public PilhaProdutos() {
        this.base = this.topo = null;
        this.tamanhoPilha = 0;
    }

    public Boolean pilhaEstaVazia(){
        return  this.base == null && this.topo == null;
    }

    public void emplilharProduto(String nome, String descricao, long idCliente,double preco, int quantidade){
        if(pilhaEstaVazia()){
            this.base = this.topo = new NoPilhaProduto(nome, descricao, idCliente, preco, quantidade);
            this.tamanhoPilha += 1;
        }else{
            this.topo.setProximoNo(new NoPilhaProduto(nome, descricao, idCliente, preco, quantidade));
            this.topo = this.topo.getProximoNo();
            this.tamanhoPilha += 1;
        }
    }

    public NoPilhaProduto desempilharProduto(){
        if(pilhaEstaVazia()){
            return null;
        }else{
            NoPilhaProduto ponteiro = this.base;
            while(this.base != this.topo && ponteiro.getProximoNo() != this.topo){
                ponteiro = ponteiro.getProximoNo();
            }
            if (this.base == this.topo){
                this.base = this.topo = null;
                System.out.println("zerou");
                return ponteiro;
            }else{
                this.topo = ponteiro;
                this.topo.setProximoNo(null);
                this.tamanhoPilha -= 1;
                return ponteiro;
            }

        }
    }

    public void mostrarProdutos(){
        if(!pilhaEstaVazia()){
            NoPilhaProduto ponteiro = this.base;
            while(ponteiro.getProximoNo() != this.topo) {
                System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                        " - " + ponteiro.getQuantidade() + " - " + ponteiro.getIdCliente());
                ponteiro = ponteiro.getProximoNo();
            }
            System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                    " - " + ponteiro.getQuantidade() + " - " + ponteiro.getIdCliente());

            System.out.println(this.topo.getNome() + " - " + this.topo.getDescricao() + " - " + this.topo.getPreco() +
                    " - " + this.topo.getQuantidade() + " - " + this.topo.getIdCliente() );
        }
    }

}
