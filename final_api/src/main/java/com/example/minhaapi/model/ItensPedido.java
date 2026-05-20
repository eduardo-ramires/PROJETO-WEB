package com.example.minhaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class ItensPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    @ManyToOne
    @JoinColumn (name = "produtoId", nullable = false)
    @JsonBackReference
    private Produto produto;
    @ManyToOne
    @JoinColumn (name = "pedidoId", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    public ItensPedido() {
    }

    public ItensPedido(int quantidade, Produto produto, Pedido pedido) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
    }

    public ItensPedido(Long id, int quantidade, Produto produto, Pedido pedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
