package com.example.minhaapi.repository;

import com.example.minhaapi.model.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {

    List<ItensPedido> findByPedidoId(Long pedidoId);
}