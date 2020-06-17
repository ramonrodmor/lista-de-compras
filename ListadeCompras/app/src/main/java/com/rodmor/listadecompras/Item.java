package com.rodmor.listadecompras;

public class Item {
    private int id;
    private String nome;
    private int categoria;
    private int quantidade;
    private float preco;
    private int selecionado;

    public Item(float preco) {
        setId(0);
        setNome("avulso");
        setCategoria(0);
        setQuantidade(1);
        setPreco(preco);
        setSelecionado(1);
    }
    public Item(String nome, int categoria, int quantidade, float preco) {
        setId(0);
        setNome(nome);
        setCategoria(categoria);
        setQuantidade(quantidade);
        setPreco(preco);
        setSelecionado(0);
    }

    public Item(int id, String nome, int categoria, int quantidade, float preco, int selecionado) {
        setId(id);
        setNome(nome);
        setCategoria(categoria);
        setQuantidade(quantidade);
        setPreco(preco);
        setSelecionado(selecionado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            this.quantidade = 0;
        } else {
            this.quantidade = quantidade;
        }
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        if (preco < 0.0) {
            this.preco = 0.0f;
        } else {
            this.preco = preco;
        }
    }

    public int getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(int selecionado) {
        if (selecionado == 1 || selecionado == 0) {
            this.selecionado = selecionado;
        } else if (selecionado > 1) {
            this.selecionado = 1;
        } else if (selecionado < 0) {
            this.selecionado = 0;
        }
    }
}
