package com.app.finance.controllers;

import com.app.finance.dto.CompradorEBancoTotalDTO;
import com.app.finance.entities.Conta;
import com.app.finance.repositories.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello, world!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(@PathVariable Long id) {
        return repository.findById(id)
                .map (conta -> ResponseEntity.ok(conta))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<?> getAllBills() {
        return repository.findAll();
    }

    @GetMapping("/comprador")
    public List<Conta> getContasPorComprador(@RequestParam String comprador) {
        return repository.findByComprador(comprador);
    }

    @GetMapping("/parcial-bills")
    public List<Conta> getUltimasQuatroContas() {
        List<Conta> contas = repository.findAll();

        contas.sort(Comparator.comparing(Conta::getId).reversed());

        return contas.stream()
                .limit(4)
                .collect(Collectors.toList());
    }

    @GetMapping("/banco")
    public List<Conta> getContasPorBanco(@RequestParam String banco) {
        return repository.findByBanco(banco);
    }

    @GetMapping("/filter")
    public List<Conta> getContasPorBancoEComprador(@RequestParam String comprador, @RequestParam String banco) {
        return repository.findByCompradorAndBanco(comprador, banco);
    }

    @GetMapping("/getallbanks")
    public List<CompradorEBancoTotalDTO> getSomaTotalPorCompradorEBanco() {
        return repository.getSomaTotalPorCompradorEBanco();
    }


    @PostMapping("/save")
    public String postConta(@RequestBody Conta conta) {
        try {
        repository.save(conta);
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

    @DeleteMapping("/delete/{id}")
    public String deleteConta(@PathVariable Long id) {
        repository.deleteById(id);
        return "Deletado com sucesso!";
    }
}
