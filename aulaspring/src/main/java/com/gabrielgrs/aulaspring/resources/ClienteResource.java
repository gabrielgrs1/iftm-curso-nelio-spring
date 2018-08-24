package com.gabrielgrs.aulaspring.resources;


import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.domain.Cliente;
import com.gabrielgrs.aulaspring.services.CategoriaService;
import com.gabrielgrs.aulaspring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Cliente cliente = clienteService.buscar(id);
        return ResponseEntity.ok().body(cliente);
    }
}