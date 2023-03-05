package com.soft.BackendSpringBootJuris.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.soft.BackendSpringBootJuris.models.Peticoes;

public interface PeticaoRepository extends JpaRepository<Peticoes, Long> {
    // Métodos do repositório podem ser definidos aqui
}

