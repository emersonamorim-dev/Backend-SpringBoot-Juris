package com.soft.BackendSpringBootJuris.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soft.BackendSpringBootJuris.models.Peticoes;
import com.soft.BackendSpringBootJuris.services.repository.PeticaoRepository;

import java.util.List;

@Service
public class PeticaoService {

    @Autowired
    private PeticaoRepository peticaoRepository;

    private final AmqpTemplate rabbitTemplate;
    private final String queueName;


    public PeticaoService(PeticaoRepository peticaoRepository, AmqpTemplate rabbitTemplate,
                          @Value("${spring.rabbitmq.queue}") String queueName) {
        this.peticaoRepository = peticaoRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public Peticoes criarPeticao(Peticoes peticao) {
        Peticoes novaPeticao = peticaoRepository.save(peticao);
        rabbitTemplate.convertAndSend(queueName, novaPeticao);
        return novaPeticao;
    }

    public List<Peticoes> obterPeticoes() {
        return peticaoRepository.findAll();
    }

    public Peticoes obterPeticaoPorId(Long id) {
        return peticaoRepository.findById(id).orElse(null);
    }

    public Peticoes atualizarPeticao(Long id, Peticoes peticaoAtualizada) {
        Peticoes peticaoExistente = peticaoRepository.findById(id).orElse(null);
        if (peticaoExistente != null) {
            peticaoExistente.setAssunto(peticaoAtualizada.getAssunto());
            peticaoExistente.setDescricao(peticaoAtualizada.getDescricao());
            return peticaoRepository.save(peticaoExistente);
        } else {
            return null;
        }
    }

    public boolean excluirPeticao(Long id) {
        Peticoes peticaoExistente = peticaoRepository.findById(id).orElse(null);
        if (peticaoExistente != null) {
            peticaoRepository.delete(peticaoExistente);
            return true;
        } else {
            return false;
        }
    }
}

