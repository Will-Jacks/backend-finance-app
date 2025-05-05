package com.app.finance.repositories;

import com.app.finance.dto.CompradorEBancoTotalDTO;
import com.app.finance.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT c FROM Conta c WHERE c.comprador LIKE %:comprador%")
    List<Conta> findByComprador(@Param("comprador") String comprador);

    @Query("SELECT c FROM Conta c WHERE c.banco LIKE %:banco%")
    List<Conta> findByBanco(@Param("banco") String banco);

    @Query("SELECT c FROM Conta c WHERE c.comprador LIKE %:comprador% AND c.banco LIKE %:banco%")
    List<Conta> findByCompradorAndBanco(@Param("comprador") String comprador, @Param("banco") String banco);


    @Query("SELECT new com.app.finance.dto.CompradorEBancoTotalDTO(c.comprador, c.banco, SUM(c.valor)) " +
            "FROM Conta c WHERE c.isPaid = false GROUP BY c.comprador, c.banco")
    List<CompradorEBancoTotalDTO> getSomaTotalPorCompradorEBanco();

    List<Conta> findByIsPaidTrue(); // Spring Data JPA entende isso automaticamente

    @Query("SELECT new com.app.finance.dto.CompradorEBancoTotalDTO(c.comprador, c.banco, SUM(c.valor)) " +
            "FROM Conta c WHERE c.isPaid = false AND c.data >= :inicio AND c.data <= :fim " +
            "GROUP BY c.comprador, c.banco")
    List<CompradorEBancoTotalDTO> getSomaTotalPorCompradorEBancoNoPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    List<Conta> findAllByDataBetween(LocalDate inicio, LocalDate fim);
}
