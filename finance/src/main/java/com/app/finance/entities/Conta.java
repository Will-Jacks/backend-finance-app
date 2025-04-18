package com.app.finance.entities;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private float valor;
    private String banco;
    private String comprador;
    private String categoria;
    private Boolean isPaid = false;
    private String data;
    private String hora;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getValor() {
        return valor;
    }

    public String getBanco() {
        return banco;
    }
    public String getComprador(){
        return comprador;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }


    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean paid) {
        isPaid = paid;
    }
}


