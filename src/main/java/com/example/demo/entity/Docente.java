package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;


@Entity
@Data
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(nullable = false)
    private String cognome;


    @Column(nullable = false, unique = true)
    private Date dataDiNascita;


    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH},mappedBy = "docente")
    private List<Corso> corsi;

    /* costruttori */
    public Docente() {}
    public Docente(String nome, String cognome, Date dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }


}
