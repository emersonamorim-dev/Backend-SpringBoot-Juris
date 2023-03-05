package com.soft.BackendSpringBootJuris.services.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.soft.BackendSpringBootJuris.models.Prazos;


public interface PrazoRepository extends JpaRepository<Prazos, Long> {

    List<Prazos> findByConcluidoAndDataLimiteLessThan(boolean b, LocalDate hoje);
}
