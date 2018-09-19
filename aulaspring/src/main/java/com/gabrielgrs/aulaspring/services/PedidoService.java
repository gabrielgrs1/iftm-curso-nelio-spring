package com.gabrielgrs.aulaspring.services;

import com.gabrielgrs.aulaspring.domain.Pedido;
import com.gabrielgrs.aulaspring.repositories.PedidoRepository;
import com.gabrielgrs.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
                + ", Tipo: " + Pedido.class.getName()));
    }
}
