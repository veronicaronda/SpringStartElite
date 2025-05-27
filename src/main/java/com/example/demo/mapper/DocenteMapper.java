package com.example.demo.mapper;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    Docente toEntity(DocenteDTO docenteDto);

    DocenteDTO toDto(Docente docente);

    void updateDocente(DocenteDTO docenteDto, @MappingTarget Docente docente);
}
