package com.app.finance.repositories;

import com.app.finance.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT c FROM Conta c WHERE c.comprador LIKE %:comprador%")
    List<Conta> findByComprador(@Param("comprador") String comprador);

    @Query("SELECT c FROM Conta c WHERE c.banco LIKE %:banco%")
    List<Conta> findByBanco(@Param("banco") String banco);

    @Query("SELECT c FROM Conta c WHERE c.comprador LIKE %:comprador% AND c.banco LIKE %:banco%")
    List<Conta> findByCompradorAndBanco(@Param("comprador") String comprador, @Param("banco") String banco);
}
