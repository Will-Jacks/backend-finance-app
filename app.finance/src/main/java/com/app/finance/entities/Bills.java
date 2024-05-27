package com.app.finance.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Float value;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "state")
    private Integer state;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Users user;
}
