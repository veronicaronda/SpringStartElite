package com.example.demo.dto;

import lombok.Data;

@Data
public class CorsoWithoutAlunniDTO {
    private Long id;
    private String nome;
    private Integer ore;
    private Integer annoAccademico;
    private DocenteDTO docente;
}
