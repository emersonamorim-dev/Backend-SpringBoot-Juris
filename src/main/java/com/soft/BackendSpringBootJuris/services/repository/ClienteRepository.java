package com.soft.BackendSpringBootJuris.services.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.BackendSpringBootJuris.models.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {

}
