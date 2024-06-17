package com.models.av3_estrutura_de_dados.Entities;

public class TabelaMeusPedidosConsumidorModel {
    private final String nomeProduto;
    private final double precoProduto;
    private final String nomeVendedor;
    private final long idVendedor;

    public TabelaMeusPedidosConsumidorModel(String nomeProduto, double precoProduto, String nomeVendedor,
                                            long idVendedor) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.nomeVendedor = nomeVendedor;
        this.idVendedor = idVendedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
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
