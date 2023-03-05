package com.soft.BackendSpringBootJuris.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.BackendSpringBootJuris.models.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    public default void saveAll(List<Usuarios> of) {
    }
    
}


