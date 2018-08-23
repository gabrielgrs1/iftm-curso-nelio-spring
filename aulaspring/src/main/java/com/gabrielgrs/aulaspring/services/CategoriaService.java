package com.gabrielgrs.aulaspring.services;

import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);


        return categoria.orElse(null);
    }
}
