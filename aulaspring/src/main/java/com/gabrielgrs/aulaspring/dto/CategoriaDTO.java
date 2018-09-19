package com.gabrielgrs.aulaspring.dto;

import com.gabrielgrs.aulaspring.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private Integer id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        id = categoria.getId();
        nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaDTO() {
    }
}
