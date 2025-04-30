package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Alunni")
public class Alunno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private Date dataDiNascita;

    @Column(name="citta_residenza", nullable = false)
    private String cittaDiResidenza;

    @Column(nullable = false)
    private double voto;

    public Alunno(){}

    public Alunno(String nome, String cognome, Date dataDiNascita, String cittaDiResidenza){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.cittaDiResidenza = cittaDiResidenza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCittaDiResidenza() {
        return cittaDiResidenza;
    }

    public void setCittaDiResidenza(String cittaDiResidenza) {
        this.cittaDiResidenza = cittaDiResidenza;
    }

    public double getVoto() {
        return voto;
    }

    public void setVoto(double voto) {
        this.voto = voto;
    }
}
