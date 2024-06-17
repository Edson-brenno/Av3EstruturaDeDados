package com.models.av3_estrutura_de_dados.Entities.Filas;

import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;

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
}
