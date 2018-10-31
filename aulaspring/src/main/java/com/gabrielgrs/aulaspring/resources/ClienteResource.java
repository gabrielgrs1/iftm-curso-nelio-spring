package com.gabrielgrs.aulaspring.resources;


import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.domain.Cliente;
import com.gabrielgrs.aulaspring.dto.CategoriaDTO;
import com.gabrielgrs.aulaspring.dto.ClienteDTO;
import com.gabrielgrs.aulaspring.dto.ClienteNewDTO;
import com.gabrielgrs.aulaspring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        cliente = clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDTO) {
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente = clienteService.insert(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<Cliente> clienteList = clienteService.findAll();
        List<ClienteDTO> clienteDTOList = clienteList.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOList);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> clientePage = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> clienteDTOS = clientePage.map(ClienteDTO::new);
        return ResponseEntity.ok().body(clienteDTOS);
    }
}
