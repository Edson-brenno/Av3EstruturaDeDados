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
            this.base = this.topo.getProximoNo();
        }
    }
}
