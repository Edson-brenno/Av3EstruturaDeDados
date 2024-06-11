package com.models.av3_estrutura_de_dados.Entities.Listas.NosListas;

import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;

public class NoCliente {
    private String nomeCompleto, email, nomeUsuario, senha;
    private TipoClienteEnum tipoCliente;
    private NoCliente proximoCliente, clienteAnterior;

    public NoCliente(String nome, String email, String nomeUsuario, String senha, TipoClienteEnum tipoCliente) {
        this.nomeCompleto = nome;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoCliente = tipoCliente;
        this.proximoCliente = this.clienteAnterior = null;
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

    public String getNomeUsuario() {
        return this.nomeUsuario;
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
