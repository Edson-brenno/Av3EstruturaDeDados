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

    public void emplilharProduto(String nome, String descricao, long idCliente,double preco){
        if(pilhaEstaVazia()){
            this.base = this.topo = new NoPilhaProduto(nome, descricao, idCliente, preco);
            this.tamanhoPilha += 1;
        }else{
            this.topo.setProximoNo(new NoPilhaProduto(nome, descricao, idCliente, preco));
            this.topo = this.topo.getProximoNo();
            this.tamanhoPilha += 1;
        }
    }

    public NoPilhaProduto desempilharProduto(){
        if(pilhaEstaVazia()){
            return null;
        }else{
            NoPilhaProduto ponteiro = this.base;

            while(ponteiro.getProximoNo() != null && ponteiro.getProximoNo() != this.topo){
                ponteiro = ponteiro.getProximoNo();
            }
            this.topo = ponteiro;
            this.tamanhoPilha -= 1;
            if(ponteiro.getProximoNo() == null){
                NoPilhaProduto produtoRetorno = ponteiro;
                this.base = this.topo = null;
                return produtoRetorno;
            }else{
                NoPilhaProduto produtoRero = ponteiro.getProximoNo();
                this.topo.setProximoNo(null);
                return produtoRero;
            }



        }
    }

    public void mostrarProdutos(){
        if(!pilhaEstaVazia()){
            NoPilhaProduto ponteiro = this.base;
            while(ponteiro.getProximoNo() != this.topo) {
                System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                         " - " + ponteiro.getIdCliente());
                ponteiro = ponteiro.getProximoNo();
            }
            System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                    " - " + ponteiro.getIdCliente());

            System.out.println(this.topo.getNome() + " - " + this.topo.getDescricao() + " - " + this.topo.getPreco() +
                   " - " + this.topo.getIdCliente() );
        }
    }

}
