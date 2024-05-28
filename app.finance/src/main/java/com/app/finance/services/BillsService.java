package com.app.finance.services;

import com.app.finance.entities.Bills;
import com.app.finance.repositories.BillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillsService {

    private final BillsRepository repository;
    public BillsService(BillsRepository billsRepository) {
        this.repository = billsRepository;
    }

    public List<Bills> getBills(){
        return repository.findAll();
    }

    public Bills getBillById(Long id) {
        Optional<Bills> optionalBills = repository.findById(id);
        return optionalBills.orElse(null);
    }

    public List<Bills> getBillsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void saveBills(Bills bill){
        repository.save(bill);
    }

    public void deleteBill(Long id){
        repository.deleteById((id));
    }
}
