package com.app.finance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conta")
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
    private String data;
    private String hora;
    private Boolean isPaid;


    public void updateIsPaid(Boolean param) {
        setIsPaid(param);
    }
}


