package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Corso")
public class Corso {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(nullable = false)
    private int ore;

    @Getter
    @Setter
    @Column(nullable = false)
    private int annoAccademico;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "id_docente")
    private Docente docente;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, mappedBy = "corsi")
    private List<Alunno> alunni;

    public Corso(){}

    public Corso(String nome, int ore, int annoAccademico){
        this.nome = nome;
        this.ore=ore;
        this.annoAccademico = annoAccademico;
    }


}
