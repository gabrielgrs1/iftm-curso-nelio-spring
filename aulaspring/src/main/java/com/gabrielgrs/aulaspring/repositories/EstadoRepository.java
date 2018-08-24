package com.gabrielgrs.aulaspring.repositories;

import com.gabrielgrs.aulaspring.domain.Cidade;
import com.gabrielgrs.aulaspring.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
