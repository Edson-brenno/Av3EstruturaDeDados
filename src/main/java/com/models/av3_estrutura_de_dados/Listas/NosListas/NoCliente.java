package com.models.av3_estrutura_de_dados.Listas.NosListas;

import com.models.av3_estrutura_de_dados.Listas.Enum.TipoClienteEnum;

public class NoCliente {
    private String nomeCompleto, email, nomeUsuario, senha;
    private TipoClienteEnum tipoCliente;
    private NoCliente proximoCliente, clienteAnterior;

    public NoCliente(String nome, String email, String senha, TipoClienteEnum tipoCliente) {
        this.nomeCompleto = nome;
        this.email = email;
        this.senha = senha;
        this.tipoCliente = tipoCliente;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public TipoClienteEnum getTipoCliente() {
        return tipoCliente;
    }

    public NoCliente getProximoCliente() {
        return proximoCliente;
    }

    public void setProximoCliente(NoCliente proximoCliente) {
        this.proximoCliente = proximoCliente;
    }

    public NoCliente getClienteAnterior() {
        return clienteAnterior;
    }

    public void setClienteAnterior(NoCliente clienteAnterior) {
        this.clienteAnterior = clienteAnterior;
    }
}
