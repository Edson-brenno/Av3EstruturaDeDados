package com.models.av3_estrutura_de_dados.Entities;

public class TabelaProdutosAComprarModel {
    private final String nomeProduto;
    private final String descricaoProduto;
    private final double precoProduto;
    private final String nomeVendedor;
    private final long idVendedor;

    public TabelaProdutosAComprarModel(String nomeProduto, String descricaoProduto, double precoProduto,
                                       String nomeVendedor, long idVendedor) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.nomeVendedor = nomeVendedor;
        this.idVendedor = idVendedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public long getIdVendedor() {
        return idVendedor;
    }

}
