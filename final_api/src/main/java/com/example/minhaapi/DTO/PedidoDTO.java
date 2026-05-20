package com.example.minhaapi.DTO;

import com.example.minhaapi.model.ItensPedido;

import java.util.Date;
import java.util.List;

public record PedidoDTO(int clienteId, List<ItemPedidoDTO> itensPedido, String status,
                        Date dataPedido, Date dataEntrega) {
}