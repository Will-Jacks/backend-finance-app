package com.app.finance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private float valor;
    private int parcelas;
    private String banco;
    private String comprador;
    private String categoria;
    private Boolean isPaid = false;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private String hora;

    public Conta() {
    }

    public Conta(Conta conta) {
        this.id = conta.id;
        this.titulo = conta.titulo;
        this.valor = conta.valor;
        this.parcelas = conta.parcelas;
        this.banco = conta.banco;
        this.comprador = conta.comprador;
        this.categoria = conta.categoria;
        this.isPaid = conta.isPaid;
        this.data = conta.data;
        this.hora = conta.hora;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public String getBanco() {
        return banco;
    }

    public String getComprador() {
        return comprador;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    @JsonProperty("isPaid")
    public Boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean paid) {
        isPaid = paid;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
//    @Override
//    public String toString() {
//        return "Conta{" +
//                "id=" + id +
//                ", titulo='" + titulo + '\'' +
//                ", valor=" + valor +
//                ", parcelas=" + parcelas +
//                ", banco='" + banco + '\'' +
//                ", comprador='" + comprador + '\'' +
//                ", categoria='" + categoria + '\'' +
//                ", isPaid=" + isPaid +
//                ", data=" + data +
//                ", hora=" + hora +
//                '}';
//    }



