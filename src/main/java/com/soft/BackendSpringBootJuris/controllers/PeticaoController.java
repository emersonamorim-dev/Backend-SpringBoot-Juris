package com.soft.BackendSpringBootJuris.controllers;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.soft.BackendSpringBootJuris.models.Peticoes;
import com.soft.BackendSpringBootJuris.services.repository.PeticaoRepository;

@RestController
@RequestMapping("/peticoes")
public class PeticaoController {

    @Autowired
    private final PeticaoRepository peticaoRepository;
    private final RabbitTemplate rabbitTemplate;

    public PeticaoController(PeticaoRepository peticaoRepository, RabbitTemplate rabbitTemplate) {
        this.peticaoRepository = peticaoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Peticoes> criarPeticao(@RequestBody Peticoes peticao) {
        Peticoes novaPeticao = peticaoRepository.save(peticao);
        rabbitTemplate.convertAndSend("peticoes", novaPeticao.getId());
        return ResponseEntity.ok(novaPeticao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peticoes> buscarPeticao(@PathVariable Long id) {
        Optional<Peticoes> peticao = peticaoRepository.findById(id);
        if (peticao.isPresent()) {
            return ResponseEntity.ok(peticao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Peticoes atualizarPeticao(Peticoes peticaoAtualizada) {
        Peticoes peticao = peticaoRepository.findById(peticaoAtualizada.getId()).orElse(null);
        if (peticao != null) {
            peticao.setDescricao(peticaoAtualizada.getDescricao());
            peticao.setAssunto(peticaoAtualizada.getAssunto());
            return peticaoRepository.save(peticao);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deletarPeticao(Long id) {
        peticaoRepository.deleteById(id);
    }
}
