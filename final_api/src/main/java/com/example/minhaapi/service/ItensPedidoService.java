package com.example.minhaapi.service;

import com.example.minhaapi.model.ItensPedido;
import com.example.minhaapi.repository.ItensPedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItensPedidoService {

    private final ItensPedidoRepository repository;

    public ItensPedidoService(ItensPedidoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ItensPedido salvar(ItensPedido item) {
       return repository.save(item);
    }

    public List<ItensPedido> listarTodos() {
        return repository.findAll();
    }

    public Optional<ItensPedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<ItensPedido> buscarPorPedido(Long pedidoId) {
        return repository.findByPedidoId(pedidoId);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}