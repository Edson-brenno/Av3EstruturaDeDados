package com.models.av3_estrutura_de_dados.Entities.Arvores.NosAvores;

public class NoAvoreCompraCliente {
    private String nomeProduto;
    private double precoProduto;
    private int idVendedor;
    private int idCliente;
    private NoAvoreCompraCliente esquerda, direita;

    public NoAvoreCompraCliente(String nomeProduto, double precoProduto, int idVendedor, int idCliente) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.esquerda = this.direita = null;
    }
}
