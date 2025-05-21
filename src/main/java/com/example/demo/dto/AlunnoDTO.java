package com.example.demo.dto;

import com.example.demo.entity.Corso;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
public class AlunnoDTO {
    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String cittaDiResidenza;
    private Double voto;
    private List<Long> corsiIds;
    private List<CorsoDTO> corsi;
}
