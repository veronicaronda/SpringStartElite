package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data

@Table(name = "Corso")
public class Corso {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int ore;


    @Column(nullable = false)
    private int annoAccademico;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "id_docente")
    private Docente docente;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, mappedBy = "corsi")
    private List<Alunno> alunni;

    public Corso(){}

    public Corso(String nome, int ore, int annoAccademico){
        this.nome = nome;
        this.ore=ore;
        this.annoAccademico = annoAccademico;
    }


}
