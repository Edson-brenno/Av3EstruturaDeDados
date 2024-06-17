package com.models.av3_estrutura_de_dados.Entities.Arvores.NosAvores;

public class NoAvoreCompraCliente {
    private String nomeProduto;
    private double precoProduto;
    private Long idVendedor;
    private Long idCliente;
    private NoAvoreCompraCliente esquerda, direita;

    public NoAvoreCompraCliente(String nomeProduto, double precoProduto, Long idVendedor, Long idCliente) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.esquerda = this.direita = null;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public NoAvoreCompraCliente getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoAvoreCompraCliente esquerda) {
        this.esquerda = esquerda;
    }

    public NoAvoreCompraCliente getDireita() {
        return direita;
    }

    public void setDireita(NoAvoreCompraCliente direita) {
        this.direita = direita;
    }
}
