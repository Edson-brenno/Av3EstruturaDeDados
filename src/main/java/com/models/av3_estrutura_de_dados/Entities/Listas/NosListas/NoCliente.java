package com.models.av3_estrutura_de_dados.Entities.Listas.NosListas;

import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;

public class NoCliente {
    private long id;
    private String nomeCompleto, email, senha;
    private TipoClienteEnum tipoCliente;
    private NoCliente proximoCliente, clienteAnterior;

    public NoCliente(long id,String nome, String email, String senha, TipoClienteEnum tipoCliente) {
        this.id = id;
        this.nomeCompleto = nome;
        this.email = email;
        this.senha = senha;
        this.tipoCliente = tipoCliente;
        this.proximoCliente = this.clienteAnterior = null;
    }

    public Long getId() {
        return this.id;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public TipoClienteEnum getTipoCliente() {
        return this.tipoCliente;
    }

    public NoCliente getProximoCliente() {
        return this.proximoCliente;
    }

    public void setProximoCliente(NoCliente proximoCliente) {
        this.proximoCliente = proximoCliente;
    }

    public NoCliente getClienteAnterior() {
        return this.clienteAnterior;
    }

    public void setClienteAnterior(NoCliente clienteAnterior) {
        this.clienteAnterior = clienteAnterior;
    }
}
