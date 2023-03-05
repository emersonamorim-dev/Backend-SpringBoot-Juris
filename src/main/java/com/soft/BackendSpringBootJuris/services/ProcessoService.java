package com.soft.BackendSpringBootJuris.services;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.soft.BackendSpringBootJuris.models.Processos;
import com.soft.BackendSpringBootJuris.services.repository.ProcessoRepository;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;
    
    private final AmqpTemplate rabbitTemplate;
    private final String queueName;

    public ProcessoService(ProcessoRepository processoRepository, AmqpTemplate rabbitTemplate,
                           @Value("${spring.rabbitmq.queue}") String queueName) {
        this.processoRepository = processoRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public Processos criarProcesso(Processos processo) {
        Processos novoProcesso = processoRepository.save(processo);
        rabbitTemplate.convertAndSend(queueName, novoProcesso);
        return novoProcesso;
    }


    public Processos buscarProcesso(Long id) {
        return processoRepository.findById(id).orElse(null);
    }

    public Processos atualizarProcesso(Processos processoAtualizado) {
        Processos processo = processoRepository.findById(processoAtualizado.getId()).orElse(null);
        if (processo != null) {
            processo.setNome(processoAtualizado.getNome());
            processo.setDescricao(processoAtualizado.getDescricao());
            processo.setAdv(processoAtualizado.getAdv());
            return processoRepository.save(processo);
        } else {
            return null;
        }
    }

    public void deletarProcesso(Long id) {
        processoRepository.deleteById(id);
    }
}
