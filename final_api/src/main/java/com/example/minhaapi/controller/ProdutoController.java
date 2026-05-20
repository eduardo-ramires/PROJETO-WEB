package com.example.minhaapi.controller;
import com.example.minhaapi.model.Produto;
import com.example.minhaapi.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto salvar(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return service.salvar(produto);
    }

    @DeleteMapping("/{id}")
    public Void remover(@PathVariable Long id) {
        service.deletar(id);
        return null;
    }
}