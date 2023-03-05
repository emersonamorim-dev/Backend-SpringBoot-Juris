package com.soft.BackendSpringBootJuris.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.soft.BackendSpringBootJuris.models.Processos;
import com.soft.BackendSpringBootJuris.services.ProcessoService;

// Configuração do controlador REST
@RestController
@RequestMapping("/processos")
public class ProcessoController {
    private final ProcessoService processoService;

    public ProcessoController(ProcessoService processoService) {
        this.processoService = processoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Processos> criarProcesso(@RequestBody Processos processo) {
        Processos novoProcesso = processoService.criarProcesso(processo);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/processos/{id}").buildAndExpand(novoProcesso.getId()).toUri()).body(novoProcesso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processos> buscarProcesso(@PathVariable Long id) {
        Processos processo = processoService.buscarProcesso(id);
        if (processo != null) {
            return ResponseEntity.ok(processo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Processos> atualizarProcesso(@PathVariable Long id, @RequestBody Processos processosAtualizado) {
        Processos processo = processoService.buscarProcesso(id);
        if (processo != null) {
            processosAtualizado.setId(id);
            Processos processoAtualizadoDB = processoService.atualizarProcesso(processosAtualizado);
            return ResponseEntity.ok(processoAtualizadoDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Long id) {
        Processos processo = processoService.buscarProcesso(id);
        if (processo != null) {
            processoService.deletarProcesso(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
