package com.gabrielgrs.aulaspring.services;

import com.gabrielgrs.aulaspring.domain.Cidade;
import com.gabrielgrs.aulaspring.domain.Cliente;
import com.gabrielgrs.aulaspring.domain.Endereco;
import com.gabrielgrs.aulaspring.domain.enuns.TipoCliente;
import com.gabrielgrs.aulaspring.dto.ClienteDTO;
import com.gabrielgrs.aulaspring.dto.ClienteNewDTO;
import com.gabrielgrs.aulaspring.repositories.CidadeRepository;
import com.gabrielgrs.aulaspring.repositories.ClienteRepository;
import com.gabrielgrs.aulaspring.repositories.EnderecoRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
                + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());


        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteNew = find(cliente.getId());
        updateData(clienteNew, cliente);
        return clienteRepository.save(clienteNew);
    }

    private void updateData(Cliente clienteNew, Cliente cliente) {
        clienteNew.setNome(cliente.getNome());
        clienteNew.setEmail(cliente.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados!");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo()));
        Cidade cidade = cidadeRepository.findById(clienteDTO.getCidadeId()).get();
        Endereco endereco = new Endereco(clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteDTO.getTelefone1());
        if (clienteDTO.getTelefone2() != null )  {
            cliente.getTelefones().add(clienteDTO.getTelefone2());

        }

        if (clienteDTO.getTelefone3() != null )  {
            cliente.getTelefones().add(clienteDTO.getTelefone3());

        }
        return cliente;

    }
}
