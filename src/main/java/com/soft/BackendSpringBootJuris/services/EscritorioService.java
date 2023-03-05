package com.soft.BackendSpringBootJuris.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soft.BackendSpringBootJuris.models.Escritorios;
import com.soft.BackendSpringBootJuris.services.repository.EscritorioRepository;

@Service
public class EscritorioService {

    @Autowired
    private EscritorioRepository escritorioRepository;
    
    private final AmqpTemplate rabbitTemplate;
    private final String queueName;

    public EscritorioService(EscritorioRepository escritorioRepository, AmqpTemplate rabbitTemplate,
                             @Value("${spring.rabbitmq.queue}") String queueName) {
        this.escritorioRepository = escritorioRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public Escritorios criarEscritorio(Escritorios escritorio) {
        Escritorios novoEscritorio = escritorioRepository.save(escritorio);
        rabbitTemplate.convertAndSend(queueName, novoEscritorio);
        return novoEscritorio;
    }

    public Escritorios buscarEscritorio(Long id) {
        return escritorioRepository.findById(id).orElse(null);
    }

    public Escritorios atualizarEscritorio(Escritorios escritorioAtualizado) {
        Escritorios escritorio = escritorioRepository.findById(escritorioAtualizado.getId()).orElse(null);
        if (escritorio != null) {
            escritorio.setNome(escritorioAtualizado.getNome());
            escritorio.setEndereco(escritorioAtualizado.getEndereco());
            escritorio.setTelefone(escritorioAtualizado.getTelefone());
            return escritorioRepository.save(escritorio);
        } else {
            return null;
        }
    }

    public void deletarEscritorio(Long id) {
        escritorioRepository.deleteById(id);
    }
}