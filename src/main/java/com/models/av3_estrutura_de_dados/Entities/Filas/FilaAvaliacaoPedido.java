package com.models.av3_estrutura_de_dados.Entities.Filas;

import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;

public class FilaAvaliacaoPedido {
    private NoFilaAvaliacaoPedido inicio;
    private NoFilaAvaliacaoPedido fim;
    private int tamanhoFila;

    public FilaAvaliacaoPedido() {
        this.inicio = this.fim = null;
        this.tamanhoFila = 0;
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
    public void enfileiraPedidoAvaliar(String nomeProduto, String nomeVendedior, long idClienteConsumidor ){
        NoFilaAvaliacaoPedido novoNoAvaliacao = new NoFilaAvaliacaoPedido(nomeProduto, null,
                nomeVendedior, idClienteConsumidor);

        if(this.filaVazia()){
            this.inicio = this.fim = novoNoAvaliacao;
            this.tamanhoFila++;
        }else{
            this.fim.setProximoProdutoDaFila(novoNoAvaliacao);
            this.fim = fim.getProximoProdutoDaFila();
            this.tamanhoFila++;
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
            if(ponteiro.getProximoProdutoDaFila() == null && this.inicio == this.fim){
                NoFilaAvaliacaoPedido avaliacaoRetorno = this.inicio;
                this.inicio = this.fim = null;
                this.tamanhoFila--;
                return avaliacaoRetorno;
            }else{
                NoFilaAvaliacaoPedido avaliacaoRetorno = this.inicio;
                this.fim.setProximoProdutoDaFila(null);
                this.inicio = this.inicio.getProximoProdutoDaFila();
                this.tamanhoFila--;
                return avaliacaoRetorno;
            }
        }
    }
}
