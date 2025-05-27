package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;
@Data
public class AlunnoWithoutCorsiDTO {
    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String cittaDiResidenza;
    private Double voto;
}
