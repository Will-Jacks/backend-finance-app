package com.app.finance.repositories;

import com.app.finance.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT c FROM Conta c WHERE c.comprador LIKE %:comprador%")
    List<Conta> findByComprador(@Param("comprador") String comprador);



}
