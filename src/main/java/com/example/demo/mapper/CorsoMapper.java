package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.CorsoWithoutAlunniDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CorsoMapper {

    Corso toEntity(CorsoDTO corsoDto);

    CorsoDTO toDto(Corso corso);

    void updateCorso(CorsoDTO corsoDto, @MappingTarget Corso corso);
}
