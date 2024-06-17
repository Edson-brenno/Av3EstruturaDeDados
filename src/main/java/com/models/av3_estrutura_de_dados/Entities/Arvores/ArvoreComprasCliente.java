package com.models.av3_estrutura_de_dados.Entities.Arvores;

import com.models.av3_estrutura_de_dados.Entities.Arvores.NosAvores.NoAvoreCompraCliente;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;

public class ArvoreComprasCliente {
    private NoAvoreCompraCliente raiz;

    public ArvoreComprasCliente() {
        this.raiz = null;
    }

    public NoAvoreCompraCliente getRaiz() {
        return this.raiz;
    }

    private boolean arvoreEstaVazia(){
        return this.raiz == null;
    }

    public void adicionarCompraCliente(String nomeProduto, double precoProduto, int idVendedor, int idCliente) {
        NoAvoreCompraCliente novoNo = new NoAvoreCompraCliente(nomeProduto, precoProduto, idVendedor, idCliente);
        if (this.arvoreEstaVazia()) {
            this.raiz = novoNo;
        }else{
            NoAvoreCompraCliente atual = this.raiz;

            while(true){
                if(novoNo.getPrecoProduto() < atual.getPrecoProduto()){
                    if(atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(novoNo);
                        break;
                    }
                }else{
                    if(atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoNo);
                        break;
                    }
                }
            }
        }
    }

    private void obterPedidosEmOrdem(NoAvoreCompraCliente atual, int idCliente, PilhaProdutos pilhaProdutos){
        if(atual != null){
            obterPedidosEmOrdem(atual.getEsquerda(), idCliente, pilhaProdutos);
            if(atual.getIdCliente() == idCliente){
                pilhaProdutos.emplilharProduto(atual.getNomeProduto(),"", );
            }
        }
    }

    public void obterTodosPedidosCliente(NoAvoreCompraCliente esquerda, int idCliente, PilhaProdutos pilhaProdutos){

    }
}
