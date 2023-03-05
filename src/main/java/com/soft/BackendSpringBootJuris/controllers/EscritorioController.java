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

import com.soft.BackendSpringBootJuris.models.Escritorios;
import com.soft.BackendSpringBootJuris.services.EscritorioService;


@RestController
@RequestMapping("/escritorios")
public class EscritorioController {
    private final EscritorioService escritorioService;

    public EscritorioController(EscritorioService escritorioService) {
        this.escritorioService = escritorioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Escritorios> criarEscritorio(@RequestBody Escritorios escritorio) {
        Escritorios novoEscritorio = escritorioService.criarEscritorio(escritorio);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/escritorios/{id}").buildAndExpand(novoEscritorio.getId()).toUri()).body(novoEscritorio);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Escritorios> buscarEscritorio(@PathVariable Long id) {
        Escritorios escritorio = escritorioService.buscarEscritorio(id);
        if (escritorio != null) {
            return ResponseEntity.ok(escritorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Escritorios> atualizarEscritorio(@PathVariable Long id, @RequestBody Escritorios escritorioAtualizado) {
        escritorioAtualizado.setId(id);
        Escritorios escritorio = escritorioService.atualizarEscritorio(escritorioAtualizado);
        if (escritorio != null) {
            return ResponseEntity.ok(escritorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEscritorio(@PathVariable Long id) {
        escritorioService.deletarEscritorio(id);
        return ResponseEntity.noContent().build();
    }
}





