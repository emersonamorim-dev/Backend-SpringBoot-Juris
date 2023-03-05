package com.soft.BackendSpringBootJuris.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.BackendSpringBootJuris.models.Processos;


public interface ProcessoRepository extends JpaRepository<Processos, Long> {
}
