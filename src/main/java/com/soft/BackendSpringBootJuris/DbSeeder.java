package com.soft.BackendSpringBootJuris;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.soft.BackendSpringBootJuris.models.Clientes;
import com.soft.BackendSpringBootJuris.models.Contratos;
import com.soft.BackendSpringBootJuris.models.Escritorios;
import com.soft.BackendSpringBootJuris.models.Peticoes;
import com.soft.BackendSpringBootJuris.models.Prazos;
import com.soft.BackendSpringBootJuris.models.Processos;
import com.soft.BackendSpringBootJuris.models.Usuarios;
import com.soft.BackendSpringBootJuris.services.repository.ClienteRepository;
import com.soft.BackendSpringBootJuris.services.repository.ContratoRepository;
import com.soft.BackendSpringBootJuris.services.repository.EscritorioRepository;
import com.soft.BackendSpringBootJuris.services.repository.PeticaoRepository;
import com.soft.BackendSpringBootJuris.services.repository.PrazoRepository;
import com.soft.BackendSpringBootJuris.services.repository.ProcessoRepository;
import com.soft.BackendSpringBootJuris.services.repository.UsuarioRepository;

@SpringBootApplication
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private EscritorioRepository escritorioRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PrazoRepository prazoRepository;

    @Autowired
    private PeticaoRepository peticaoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private String endereco;

    public DbSeeder(EscritorioRepository escritorioRepository, ContratoRepository contratoRepository,
            PrazoRepository prazoRepository, PeticaoRepository peticaoRepository,
            ProcessoRepository processoRepository, UsuarioRepository usuarioRepository) {
        this.escritorioRepository = escritorioRepository;
        this.contratoRepository = contratoRepository;
        this.prazoRepository = prazoRepository;
        this.peticaoRepository = peticaoRepository;
        this.processoRepository = processoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DbSeeder.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Crie alguns usuários
        Usuarios usuarios1 = new Usuarios("Emerson", "Amorim", "emerson.amorim@email.com");
        Usuarios usuarios2 = new Usuarios("Emerson", "Luiz", "emerson.luiz@email.com");
        usuarioRepository.saveAll(List.of(usuarios1, usuarios2));

        // Crie alguns clientes
        Clientes clientes1 = new Clientes(null, "Cliente 1", "Endereço 1", "contato1@empresa.com", "123456789", endereco);
        Clientes clientes2 = new Clientes(null, "Cliente 2", "Endereço 2", "contato2@empresa.com", "987654321", endereco);
        clienteRepository.saveAll(List.of(clientes1, clientes2));

        // Crie alguns contratos
        Contratos contratos1 = new Contratos("Contrato 1", LocalDate.now().plusDays(30), clientes1);
        Contratos contratos2 = new Contratos("Contrato 2", LocalDate.now().plusDays(60), clientes2);
        List<Contratos> contratos = Arrays.asList(contratos1, contratos2);
        contratoRepository.saveAll(contratos);

        // Crie alguns escritórios
        Escritorios escritorios1 = new Escritorios("Escritório 1", "Endereço 1");
        Escritorios escritorios2 = new Escritorios("Escritório 2", "Endereço 2");
        List<Escritorios> escritorios = Arrays.asList(escritorios1, escritorios2);
        escritorioRepository.saveAll(escritorios);

        // Crie alguns prazos
        Prazos prazos1 = new Prazos("Prazo 1", LocalDate.now().plusDays(7), escritorios1);
        Prazos prazos2 = new Prazos("Prazo 2", LocalDate.now().plusDays(14), escritorios2);
        List<Prazos> prazos = Arrays.asList(prazos1, prazos2);
        prazoRepository.saveAll(prazos);
        
        // Crie algumas petições
        Peticoes peticoes1 = new Peticoes("Petição 1", "Descrição da petição 1", LocalDate.now(), usuarios1, prazos1,
                contratos1);
        Peticoes peticoes2 = new Peticoes("Petição 2", "Descrição da petição 2", LocalDate.now(), usuarios2, prazos2,
                contratos2);
        peticaoRepository.saveAll(List.of(peticoes1, peticoes2));

        // Crie alguns processos
        Processos processos1 = new Processos("Processo 1", "Descrição do processo 1", LocalDate.now().plusDays(7),
                usuarios1, escritorios1, clientes1);
        Processos processos2 = new Processos("Processo 2", "Descrição do processo 2", LocalDate.now().plusDays(14),
                usuarios2, escritorios2, clientes2);
        processoRepository.saveAll(List.of(processos1, processos2));

    }
}
