package com.app.finance.repositories;

import com.app.finance.entities.Bills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillsRepository extends JpaRepository<Bills, Long> {
    List<Bills> findByUserId(Long userId);
}
