package com.app.finance.controllers;

import com.app.finance.dto.CompradorEBancoTotalDTO;
import com.app.finance.entities.Conta;
import com.app.finance.repositories.ContaRepository;

import com.app.finance.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @GetMapping("/all")
    public List<?> allBillsOfCurrentMonth() {
        LocalDate startOfMonth = DateUtils.getStartOfCurrentMonth();
        LocalDate endOfMonth = DateUtils.getEndOfCurrentMonth();
        return repository.findAllByDataBetween(startOfMonth, endOfMonth);
    }

    @GetMapping("/parcial-bills")
    public List<Conta> lastFourBills() {
        List<Conta> contas = repository.findAll();

        contas.sort(Comparator.comparing(Conta::getId).reversed());

        return contas.stream()
                .limit(4)
                .collect(Collectors.toList());
    }

    @GetMapping("/paids")
    public List<Conta> getAllPaids() {
        return repository.findByIsPaidTrue();
    }

    @GetMapping("/totals-by-period")
    public List<CompradorEBancoTotalDTO> totalsByPeriod(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return repository.getSomaTotalPorCompradorEBancoNoPeriodo(inicio, fim);
    }

    @GetMapping("/bills-by-period")
    public List<Conta> allBillsByPeriod(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
    ) {
        return repository.findAllByDataBetweenOrderByDataAsc(inicio, fim);
    }


    @PostMapping("/save")
    public String postConta(@RequestBody Conta conta) {
        try {
            if(conta.getData() == null) {
                conta.setData(LocalDate.now());
            }
            if(conta.getParcelas() == 0) {
                repository.save(conta);
            }
            for (int i =0; i<conta.getParcelas(); i++){
                Conta novaConta = new Conta(conta);
                novaConta.setTitulo(novaConta.getTitulo() + " " + (i+1) + "/" + conta.getParcelas());
                novaConta.setData(conta.getData().plusMonths(i+1));
                repository.save(novaConta);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "Erro ao salvar";
        }
        return "Salvo com sucesso";
    }

    @PutMapping("/update")
    public String putConta(@RequestBody Conta conta) {
        try {
            Optional<Conta> existing = repository.findById(conta.getId());

            if (existing.isPresent()) {
                repository.save(conta);
                return "Conta atualizada com sucesso!";
            } else {
                return "Conta não encontrada.";
            }
        } catch (Exception e) {
            return "Erro ao atualizar a conta.";
        }
    }

    @PutMapping("/isPaid")
    public String setIsPaid(@RequestBody Conta conta) {
        try{
            Optional<Conta> existing = repository.findById(conta.getId());

            if(existing.isPresent()) {
                Conta existingConta = existing.get();
                existingConta.setIsPaid(conta.isPaid());
                repository.save(existingConta);
                return "Conta atualizada com sucesso!";
            }else {
                return "Conta não encontrada";
            }
        }catch (Exception e) {
            return "Erro ao atualizar conta";
        }

    }

    @DeleteMapping("/delete/{id}")
    public String deleteConta(@PathVariable Long id) {
        repository.deleteById(id);
        return "Deletado com sucesso!";
    }
}