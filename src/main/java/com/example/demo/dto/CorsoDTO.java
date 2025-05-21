package com.example.demo.dto;


import com.example.demo.entity.Alunno;
import lombok.Data;

import java.util.List;

@Data
public class CorsoDTO {

    private Long id;
    private String nome;
    private int ore;
    private int annoAccademico;

    private DocenteDTO docente;
    private  List<AlunnoDTO> alunni;
    private List<Long> alunniIds;
    private List<Long> corsiIds;

}
