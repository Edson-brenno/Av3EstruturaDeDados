package com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas;

import java.util.UUID;

public class NoPilhaProduto {
    private String idProduto, nome, descricao;
    private double preco;
    private Long idClienteVendedor;
    private Long idClienteComprador;
    private NoPilhaProduto proximoNo;

    public NoPilhaProduto(String nome, String descricao, Long idClienteVendedor, Long idClienteComprador,double preco) {
        this.idProduto = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.idClienteVendedor = idClienteVendedor;
        this.idClienteComprador = idClienteComprador;
        this.preco = preco;
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

    public long getIdClienteVendedor() {
        return this.idClienteVendedor;
    }
    public double getPreco() {
        return preco;
    }

    public NoPilhaProduto getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(NoPilhaProduto proximoNo){
        this.proximoNo = proximoNo;
    }


    public Long getIdClienteComprador() {
        return idClienteComprador;
    }

    public void setIdClienteComprador(Long idClienteComprador) {
        this.idClienteComprador = idClienteComprador;
    }
}
