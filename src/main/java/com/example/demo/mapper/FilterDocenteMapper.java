package com.example.demo.mapper;

import com.example.demo.dto.FilterDocenteDTO;
import com.example.demo.entity.Docente;
import org.springframework.stereotype.Component;

@Component
public class FilterDocenteMapper {
    public FilterDocenteDTO toDto (Docente docente){
        FilterDocenteDTO filterDocenteDto = new FilterDocenteDTO();
        filterDocenteDto.setNome(docente.getNome());
        filterDocenteDto.setCognome(docente.getCognome());
        return filterDocenteDto;
    }
}
