package com.soft.BackendSpringBootJuris.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soft.BackendSpringBootJuris.models.Clientes;
import com.soft.BackendSpringBootJuris.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Clientes> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> buscarClientePorId(@PathVariable Long id) {
        Clientes cliente = clienteService.buscarClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Clientes> criarCliente(@RequestBody Clientes cliente) {
        Clientes novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> atualizarCliente(@PathVariable Long id, @RequestBody Clientes clienteAtualizado) {
        Clientes clienteExistente = clienteService.buscarClientePorId(id);
        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteService.atualizarCliente(clienteExistente);
        return ResponseEntity.ok(clienteExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        Clientes clienteExistente = clienteService.buscarClientePorId(id);
        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        clienteService.deletarCliente(clienteExistente);
        return ResponseEntity.noContent().build();
    }
}
