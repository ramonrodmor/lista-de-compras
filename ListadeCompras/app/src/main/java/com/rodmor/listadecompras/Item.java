package com.rodmor.listadecompras;

public class Item {
    private String nome;
    private int categoria;
    // 1 PARA CONDIMENTOS
    // 2 PARA BOLACHAS
    // 3 PARA FRIOS
    // 4 PARA HIGIÊNE
    // 0 PARA OUTROS
    private int quantidade;
    private float preco;

    public Item(String nome, int categoria, int quantidade, float preco) {
        setNome(nome);
        setCategoria(categoria);
        setQuantidade(quantidade);
        setPreco(preco);
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
}
