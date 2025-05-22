package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Alunni")
public class Alunno {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(nullable = false)
    private String cognome;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private Date dataDiNascita;

    @Getter
    @Setter
    @Column(name="citta_residenza", nullable = false)
    private String cittaDiResidenza;

    @Getter
    @Setter
    @Column(nullable = false)
    private double voto;
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.DETACH})
    @JoinTable(
            name = "corso_alunni",
            joinColumns = {@JoinColumn(name = "id_alunni")},
            inverseJoinColumns = {@JoinColumn(name = "id_corso")}
    )
    private List<Corso> corsi;

    public Alunno(){}

    public Alunno(String nome, String cognome, Date dataDiNascita, String cittaDiResidenza){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.cittaDiResidenza = cittaDiResidenza;
    }
}
