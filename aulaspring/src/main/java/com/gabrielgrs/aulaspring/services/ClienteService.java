package com.gabrielgrs.aulaspring.services;

import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.domain.Cliente;
import com.gabrielgrs.aulaspring.repositories.CategoriaRepository;
import com.gabrielgrs.aulaspring.repositories.ClienteRepository;
import com.gabrielgrs.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
                + ", Tipo: " + Cliente.class.getName()));
    }
}
