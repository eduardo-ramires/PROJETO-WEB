package com.example.minhaapi.service;

import com.example.minhaapi.DTO.ItemPedidoDTO;
import com.example.minhaapi.DTO.PedidoDTO;
import com.example.minhaapi.model.Cliente;
import com.example.minhaapi.model.ItensPedido;
import com.example.minhaapi.model.Pedido;
import com.example.minhaapi.model.Produto;
import com.example.minhaapi.repository.ClienteRepository;
import com.example.minhaapi.repository.ItensPedidoRepository;
import com.example.minhaapi.repository.PedidoRepository;
import com.example.minhaapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItensPedidoRepository itensPedidoRepository;

    public PedidoService(PedidoRepository repository, ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository, ItensPedidoRepository itensPedidoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itensPedidoRepository = itensPedidoRepository;
    }

    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById((long) pedidoDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(pedidoDTO.status());
        pedido.setData(pedidoDTO.dataPedido());
        pedido.setDataEntrega(pedidoDTO.dataEntrega());

        Pedido pedidoSalvo = repository.save(pedido);

        double subtotal = 0;
        for (ItemPedidoDTO itemDTO : pedidoDTO.itensPedido()) {
            Produto produto = produtoRepository.findById((long) itemDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: id " + itemDTO.produtoId()));
            itensPedidoRepository.save(new ItensPedido(itemDTO.quantidade(), produto, pedidoSalvo));
            subtotal += produto.getPreco() * itemDTO.quantidade();
        }

        pedidoSalvo.setValorTotal(subtotal * 1.10);
        repository.save(pedidoSalvo);

        return pedidoSalvo;
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Pedido atualizar(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById((long) pedidoDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        pedido.setCliente(cliente);
        pedido.setStatus(pedidoDTO.status());
        pedido.setData(pedidoDTO.dataPedido());
        pedido.setDataEntrega(pedidoDTO.dataEntrega());

        itensPedidoRepository.deleteAll(itensPedidoRepository.findByPedidoId(id));

        double subtotal = 0;
        for (ItemPedidoDTO itemDTO : pedidoDTO.itensPedido()) {
            Produto produto = produtoRepository.findById((long) itemDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: id " + itemDTO.produtoId()));
            itensPedidoRepository.save(new ItensPedido(itemDTO.quantidade(), produto, pedido));
            subtotal += produto.getPreco() * itemDTO.quantidade();
        }

        pedido.setValorTotal(subtotal * 1.10);
        return repository.save(pedido);
    }

    @Transactional
    public void deletar(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        itensPedidoRepository.deleteAll(itensPedidoRepository.findByPedidoId(id));
        repository.delete(pedido);
    }
}
