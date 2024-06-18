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

    public void emplilharProduto(String nome, String descricao, Long idClienteVendedor, Long idClienteComprador, double preco){
        if(pilhaEstaVazia()){
            this.base = this.topo = new NoPilhaProduto(nome, descricao, idClienteVendedor,idClienteComprador,preco);
            this.tamanhoPilha += 1;
        }else{
            this.topo.setProximoNo(new NoPilhaProduto(nome, descricao, idClienteVendedor, idClienteComprador,preco));
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
            while(ponteiro.getProximoNo() != null && ponteiro.getProximoNo() != this.topo) {
                System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                         " - " + ponteiro.getIdClienteVendedor());
                ponteiro = ponteiro.getProximoNo();
            }
            System.out.println(ponteiro.getNome() + " - " + ponteiro.getDescricao() + " - " + ponteiro.getPreco() +
                    " - " + ponteiro.getIdClienteVendedor());

            System.out.println(this.topo.getNome() + " - " + this.topo.getDescricao() + " - " + this.topo.getPreco() +
                   " - " + this.topo.getIdClienteVendedor() );
        }
    }

    public PilhaProdutos gerarCopiaPilhaProdutos(){
        if(!this.pilhaEstaVazia()){
            PilhaProdutos copia = new PilhaProdutos();

            NoPilhaProduto ponteiro = this.base;

            while(ponteiro.getProximoNo() != null){

                copia.emplilharProduto(ponteiro.getNome(), ponteiro.getDescricao(), ponteiro.getIdClienteVendedor(),
                        null,ponteiro.getPreco());

                ponteiro = ponteiro.getProximoNo();
            }

            copia.emplilharProduto(ponteiro.getNome(), ponteiro.getDescricao(), ponteiro.getIdClienteVendedor(),
                    null,ponteiro.getPreco());

            return copia;
        }else{
            return null;
        }

    }

    public PilhaProdutos gerarCopiaPilhaProdutosIdVendedor(long idVendedor){

        if(!this.pilhaEstaVazia()){
            PilhaProdutos copia = new PilhaProdutos();

            NoPilhaProduto ponteiro = this.base;

            while(ponteiro.getProximoNo() != null){
                if(ponteiro.getIdClienteVendedor() == idVendedor){
                    copia.emplilharProduto(ponteiro.getNome(), ponteiro.getDescricao(), ponteiro.getIdClienteVendedor(),
                            null,ponteiro.getPreco());
                }

                ponteiro = ponteiro.getProximoNo();
            }

            if(ponteiro.getIdClienteVendedor() == idVendedor){
                copia.emplilharProduto(ponteiro.getNome(), ponteiro.getDescricao(), ponteiro.getIdClienteVendedor(),
                        null,ponteiro.getPreco());
            }

            return copia;

        }else{
            return null;
        }

    }

}
