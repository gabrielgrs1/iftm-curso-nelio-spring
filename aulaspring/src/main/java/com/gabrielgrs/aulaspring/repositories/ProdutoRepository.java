package com.gabrielgrs.aulaspring.repositories;

import com.gabrielgrs.aulaspring.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
