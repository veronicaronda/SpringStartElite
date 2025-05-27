package com.example.demo.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
public class DocenteWithoutCorsiDTO {

    @Id
    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;

}
