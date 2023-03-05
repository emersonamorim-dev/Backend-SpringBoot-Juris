package com.soft.BackendSpringBootJuris.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
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

import com.soft.BackendSpringBootJuris.models.Prazos;
import com.soft.BackendSpringBootJuris.services.PrazoService;

@RestController
@RequestMapping("/prazos")
public class PrazoController {
    private final PrazoService prazoService;

    public PrazoController(PrazoService prazoService) {
        this.prazoService = prazoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Prazos> criarPrazo(@RequestBody Prazos prazo) {
        Prazos novoPrazo = prazoService.criarPrazo(prazo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPrazo);
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Prazos> buscarPrazo(@PathVariable Long id) {
            Optional<Prazos> prazo = prazoService.buscarPrazo(id);
            if (prazo.isPresent()) {
                return ResponseEntity.ok(prazo.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<Prazos> atualizarPrazo(@PathVariable Long id, @RequestBody Prazos prazo) {
            Optional<Prazos> prazoExistente = prazoService.buscarPrazo(id);
            if (prazoExistente.isPresent()) {
                prazo.setId(id);
                Prazos prazoAtualizado = prazoService.atualizarPrazo(prazo);
                return ResponseEntity.ok(prazoAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> excluirPrazo(@PathVariable Long id) {
            Optional<Prazos> prazoExistente = prazoService.buscarPrazo(id);
            if (prazoExistente.isPresent()) {
                prazoService.excluirPrazo(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }        
