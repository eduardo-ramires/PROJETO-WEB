package com.example.minhaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valorTotal;
    private String status;
    private Date data;
    private Date dataEntrega;

    @ManyToOne
    @JoinColumn (name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(double valorTotal, String status, Date data, Date dataEntrega, Cliente cliente) {
        this.valorTotal = valorTotal;
        this.status = status;
        this.data = data;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
    }

    public Pedido(Long id, double valorTotal, String status, Date data, Date dataEntrega, Cliente cliente) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.status = status;
        this.data = data;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
