package com.soft.BackendSpringBootJuris.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.soft.BackendSpringBootJuris.models.Prazos;
import com.soft.BackendSpringBootJuris.services.repository.PrazoRepository;

@Service
public class PrazoService {

    @Autowired
    private PrazoRepository prazoRepository;
    
    private final AmqpTemplate rabbitTemplate;
    private final String queueName;
    

    public PrazoService(PrazoRepository prazoRepository, AmqpTemplate rabbitTemplate,
                        @Value("${spring.rabbitmq.queue}") String queueName) {
        this.prazoRepository = prazoRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public Prazos criarPrazo(Prazos prazo) {
        Prazos novoPrazo = prazoRepository.save(prazo);
        rabbitTemplate.convertAndSend(queueName, novoPrazo);
        return novoPrazo;
    }

    public Optional<Prazos> buscarPrazo(Long id) {
        return prazoRepository.findById(id);
    }

    public Prazos atualizarPrazo(Prazos prazo) {
        return prazoRepository.save(prazo);
    }

    public void excluirPrazo(Long id) {
        prazoRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void notificarPrazosVencidos() {
        LocalDate hoje = LocalDate.now();
        List<Prazos> prazosVencidos = prazoRepository.findByConcluidoAndDataLimiteLessThan(false, hoje);
        if (!prazosVencidos.isEmpty()) {
            rabbitTemplate.convertAndSend(queueName, prazosVencidos);
        }
    }
}
