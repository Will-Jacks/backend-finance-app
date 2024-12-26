package com.app.finance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private float valor;
    private String descricao;
    private String estabelecimento;
    private String formaDePagamento;
    private String banco;
    private String comprador;
    private String categoria;
    private Boolean isPaid;
}


