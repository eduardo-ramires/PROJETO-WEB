package com.example.minhaapi.controller;

import com.example.minhaapi.model.ItensPedido;
import com.example.minhaapi.service.ItensPedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-pedido")
public class ItensPedidoController {

    private final ItensPedidoService service;

    public ItensPedidoController(ItensPedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ItensPedido adicionarItem(@RequestBody ItensPedido item) {
        return service.salvar(item);
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<ItensPedido> listarPorPedido(@PathVariable Long pedidoId) {
        return service.buscarPorPedido(pedidoId);
    }
}
