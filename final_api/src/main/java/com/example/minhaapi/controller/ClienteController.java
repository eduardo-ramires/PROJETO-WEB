package com.example.minhaapi.controller;

import com.example.minhaapi.model.Cliente;
import com.example.minhaapi.model.Produto;
import com.example.minhaapi.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public Void remover(@PathVariable Long id) {
        service.deletar(id);
        return null;
    }


    @PutMapping("/{id}")
    public Cliente salvar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return service.salvar(cliente);
    }
}