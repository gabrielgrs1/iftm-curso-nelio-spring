package com.gabrielgrs.aulaspring.services;

import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.dto.CategoriaDTO;
import com.gabrielgrs.aulaspring.repositories.CategoriaRepository;
import com.gabrielgrs.aulaspring.services.exceptions.DataIntegrityException;
import com.gabrielgrs.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
                + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria categoriaNew = find(categoria.getId());
        updateData(categoriaNew, categoria);
        return categoriaRepository.save(categoriaNew);
    }

    private void updateData(Categoria categoriaNew, Categoria categoria) {
        categoriaNew.setNome(categoria.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getNome());
    }

}
