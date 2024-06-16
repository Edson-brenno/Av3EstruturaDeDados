package com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas;

import java.util.UUID;

public class NoPilhaProduto {
    private String idProduto, nome, descricao;
    private double preco;
    private double idCliente;
    private int quantidade;
    private NoPilhaProduto proximoNo;

    public NoPilhaProduto(String nome, String descricao, long idCliente,double preco, int quantidade) {
        this.idProduto = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public NoPilhaProduto getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(NoPilhaProduto proximoNo){
        this.proximoNo = proximoNo;
    }


}
