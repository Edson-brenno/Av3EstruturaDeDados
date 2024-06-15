package com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas;

import java.util.UUID;

public class NoPilhaProduto {
    private String idProduto, nome, descricao;
    private int quantidade;
    private NoPilhaProduto proximoNo;

    public NoPilhaProduto(String nome, String descricao, int quantidade) {
        this.idProduto = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.proximoNo = null;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public NoPilhaProduto getProximoNo() {
        return proximoNo;
    }

}
