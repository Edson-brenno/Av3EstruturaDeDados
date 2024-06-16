package com.models.av3_estrutura_de_dados.Entities;


public class TabelaVerProdutosVendedorModel {
    private final String nome;
    private final double preco;

    public TabelaVerProdutosVendedorModel(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return this.nome;
    }

    public Double getPreco() {
        return this.preco;
    }

}
