package com.app.finance.controllers;

import com.app.finance.entities.Conta;
import com.app.finance.repositories.ContaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
