package com.gabrielgrs.aulaspring.repositories;

import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
