package com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas;

import java.math.BigDecimal;

public class NoFilaAvaliacaoPedido {
    private String nomeProduto;
    private String descricaoAvaliacaoProduto;
    private String nomeVendedorProduto;
    private Long idClienteConsumidorProduto;
    private String idCompra;
    private Integer notaAvaliacaoProduto;
    private Boolean jaAvaliado;
    private NoFilaAvaliacaoPedido proximoProdutoDaFila;

    public NoFilaAvaliacaoPedido(String nomeProduto, String descricaoAvaliacaoProduto, String nomeVendedorProduto,
                                 long idClienteConsumidorProduto, String idCompra) {
        this.nomeProduto = nomeProduto;
        this.descricaoAvaliacaoProduto = descricaoAvaliacaoProduto;
        this.nomeVendedorProduto = nomeVendedorProduto;
        this.idClienteConsumidorProduto = idClienteConsumidorProduto;
        this.idCompra = idCompra;
        this.notaAvaliacaoProduto = 0;
        this.jaAvaliado = false;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoAvaliacaoProduto() {
        return descricaoAvaliacaoProduto;
    }

    public String getNomeVendedorProduto() {
        return nomeVendedorProduto;
    }

    public Integer getNotaAvaliacaoProduto() {
        return notaAvaliacaoProduto;
    }

    public void setNotaAvaliacaoProduto(Integer notaAvaliacaoProduto) {
        this.notaAvaliacaoProduto = notaAvaliacaoProduto;
    }

    public Boolean getJaAvaliado() {
        return jaAvaliado;
    }

    public void setJaAvaliado(Boolean jaAvaliado) {
        this.jaAvaliado = jaAvaliado;
    }

    public NoFilaAvaliacaoPedido getProximoProdutoDaFila() {
        return proximoProdutoDaFila;
    }

    public void setProximoProdutoDaFila(NoFilaAvaliacaoPedido proximoProdutoDaFila) {
        this.proximoProdutoDaFila = proximoProdutoDaFila;
    }

    public Long getIdClienteConsumidorProduto() {
        return idClienteConsumidorProduto;
    }

    public String getIdCompra() {
        return idCompra;
    }
}
