package com.gabrielgrs.aulaspring.repositories;

import com.gabrielgrs.aulaspring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
