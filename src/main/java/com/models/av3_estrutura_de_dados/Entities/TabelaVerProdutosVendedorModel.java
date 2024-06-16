package com.models.av3_estrutura_de_dados.Entities;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelaVerProdutosVendedorModel {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty nome;
    private final SimpleDoubleProperty preco;

    public TabelaVerProdutosVendedorModel(String nome, double preco){
        this.selected = new SimpleBooleanProperty(false);
        this.nome = new SimpleStringProperty(nome);
        this.preco = new SimpleDoubleProperty(preco);
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public SimpleStringProperty nomePropety() {
        return nome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public Double getPreco() {
        return preco.get();
    }

    public SimpleDoubleProperty precoPropety() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco.set(preco);
    }
}
