package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class DocenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private List<CorsoDTO> corsi;
    private List<Long> corsoIds;
}
