package com.example.minhaapi.service;

import com.example.minhaapi.model.Produto;
import com.example.minhaapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Produto salvar(Produto produto) {
       if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}