package com.example.minhaapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String endercoEntrega;
    private String preferenciaPagamento;

    public Cliente() {
    }

    public Cliente(String email, String endercoEntrega, String preferenciaPagamento) {
        this.email = email;
        this.endercoEntrega = endercoEntrega;
        this.preferenciaPagamento = preferenciaPagamento;
    }

    public Cliente(Long id, String email, String endercoEntrega, String preferenciaPagamento) {
        this.id = id;
        this.email = email;
        this.endercoEntrega = endercoEntrega;
        this.preferenciaPagamento = preferenciaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndercoEntrega() {
        return endercoEntrega;
    }

    public void setEndercoEntrega(String endercoEntrega) {
        this.endercoEntrega = endercoEntrega;
    }

    public String getPreferenciaPagamento() {
        return preferenciaPagamento;
    }

    public void setPreferenciaPagamento(String preferenciaPagamento) {
        this.preferenciaPagamento = preferenciaPagamento;
    }
}
