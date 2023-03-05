package com.soft.BackendSpringBootJuris.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.BackendSpringBootJuris.models.Escritorios;

public interface EscritorioRepository extends JpaRepository<Escritorios, Long> {
}