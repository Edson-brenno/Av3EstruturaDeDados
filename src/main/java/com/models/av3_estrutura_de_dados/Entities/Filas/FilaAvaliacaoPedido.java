package com.models.av3_estrutura_de_dados.Entities.Filas;

import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;

public class FilaAvaliacaoPedido {
    private NoFilaAvaliacaoPedido inicio;
    private NoFilaAvaliacaoPedido fim;

    public FilaAvaliacaoPedido() {
        this.inicio = this.fim = null;
    }

    public NoFilaAvaliacaoPedido getInicio() {
        return this.inicio;
    }

    public void setInicio(NoFilaAvaliacaoPedido inicio) {
        this.inicio = inicio;
    }

    public NoFilaAvaliacaoPedido getFim() {
        return this.fim;
    }

    public void setFim(NoFilaAvaliacaoPedido fim) {
        this.fim = fim;
    }

    public boolean filaVazia(){
        return this.inicio == null && this.fim == null;
    }
    public void enfileiraPedidoAvaliar(String nomeProduto, String nomeVendedior ){
        NoFilaAvaliacaoPedido novoNoAvaliacao = new NoFilaAvaliacaoPedido(nomeProduto, null,
                nomeVendedior);

        if(this.filaVazia()){
            this.inicio = this.fim = novoNoAvaliacao;
        }else{
            this.fim.setProximoProdutoDaFila(novoNoAvaliacao);
            this.fim = fim.getProximoProdutoDaFila();
        }
    }

    public NoFilaAvaliacaoPedido desenfileiraPedidoAvaliar(){
        if(this.filaVazia()){
            return null;
        }else{
            NoFilaAvaliacaoPedido ponteiro = this.inicio;

            while(ponteiro.getProximoProdutoDaFila() != null && ponteiro.getProximoProdutoDaFila() != this.inicio){
                ponteiro = ponteiro.getProximoProdutoDaFila();
            }
            this.fim = ponteiro;
            if(ponteiro.getProximoProdutoDaFila() == null){
                NoFilaAvaliacaoPedido avaliacaoRetorno = ponteiro;
                this.inicio = this.fim = null;
                return avaliacaoRetorno;
            }else{
                NoFilaAvaliacaoPedido avaliacaoRetorno = ponteiro.getProximoProdutoDaFila();
                this.fim.setProximoProdutoDaFila(null);
                return avaliacaoRetorno;
            }



        }
    }
}
