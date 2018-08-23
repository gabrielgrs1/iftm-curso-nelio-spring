package com.gabrielgrs.aulaspring.repositories;

import com.gabrielgrs.aulaspring.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
