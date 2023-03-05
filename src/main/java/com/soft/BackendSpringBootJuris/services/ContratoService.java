package com.soft.BackendSpringBootJuris.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.soft.BackendSpringBootJuris.models.Contratos;
import com.soft.BackendSpringBootJuris.services.repository.ContratoRepository;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;
    
    private final AmqpTemplate rabbitTemplate;
    private final String queueName;

    public ContratoService(ContratoRepository contratoRepository, AmqpTemplate rabbitTemplate,
                           @Value("${spring.rabbitmq.queue}") String queueName) {
        this.contratoRepository = contratoRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public Contratos criarContrato(Contratos contrato) {
        Contratos novoContrato = contratoRepository.save(contrato);
        rabbitTemplate.convertAndSend(queueName, novoContrato);
        return novoContrato;
    }

    public Contratos buscarContrato(Long id) {
        return contratoRepository.findById(id).orElse(null);
    }

    public Contratos atualizarContrato(Contratos contratoAtualizado) {
        Contratos contrato = contratoRepository.findById(contratoAtualizado.getId()).orElse(null);
        if (contrato != null) {
            contrato.setDescricao(contratoAtualizado.getDescricao());
            contrato.setDataInicio(contratoAtualizado.getDataInicio());
            contrato.setDataFim(contratoAtualizado.getDataFim());
            contrato.setValor(contratoAtualizado.getValor());
            return contratoRepository.save(contrato);
        } else {
            return null;
        }
    }

    public void deletarContrato(Long id) {
        contratoRepository.deleteById(id);
    }
}
