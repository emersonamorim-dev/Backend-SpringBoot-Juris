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

import com.soft.BackendSpringBootJuris.models.Contratos;
import com.soft.BackendSpringBootJuris.services.ContratoService;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contratos> buscarContrato(@PathVariable Long id) {
        Contratos contratos = contratoService.buscarContrato(id);
        if (contratos != null) {
            return ResponseEntity.ok(contratos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Contratos> criarContrato(@RequestBody Contratos contratos) {
        Contratos novoContrato = contratoService.criarContrato(contratos);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/contratos/{id}").buildAndExpand(novoContrato.getId()).toUri()).body(novoContrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contratos> atualizarContrato(@PathVariable Long id, @RequestBody Contratos contratoAtualizado) {
        Contratos contratos = contratoService.buscarContrato(id);
        if (contratos != null) {
            contratoAtualizado.setId(id);
            Contratos contratoAtualizadoDB = contratoService.atualizarContrato(contratoAtualizado);
            return ResponseEntity.ok(contratoAtualizadoDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
        Contratos contratos = contratoService.buscarContrato(id);
        if (contratos != null) {
            contratoService.deletarContrato(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
