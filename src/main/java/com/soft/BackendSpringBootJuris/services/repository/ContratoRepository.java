package com.soft.BackendSpringBootJuris.services.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.BackendSpringBootJuris.models.Contratos;


public interface ContratoRepository extends JpaRepository<Contratos, Long> {
}
