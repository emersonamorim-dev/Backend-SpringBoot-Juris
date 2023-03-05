package com.soft.BackendSpringBootJuris.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.BackendSpringBootJuris.models.Clientes;
import com.soft.BackendSpringBootJuris.services.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Clientes> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Clientes buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Clientes salvar(Clientes cliente) {
        return clienteRepository.save(cliente);
    }
    

    public Clientes atualizar(Long id, Clientes clienteAtualizado) {
        Clientes clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente == null) {
            return null;
        }
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        return clienteRepository.save(clienteExistente);
    }

    public void deletar(Long id) {
        Clientes clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteRepository.delete(clienteExistente);
        }
    }

    public List<Clientes> listarClientes() {
        return clienteRepository.findAll();
    }

    public Clientes buscarClientePorId(Long id) {
        Optional<Clientes> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

    public Clientes criarCliente(Clientes cliente) {
        return clienteRepository.save(cliente);
    }

    public void atualizarCliente(Clientes clienteExistente) {
        clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Clientes clienteExistente) {
        clienteRepository.delete(clienteExistente);
    }
}

