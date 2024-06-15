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
}
