package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AlunnoMapper {

    Alunno toEntity (AlunnoDTO alunnoDto);

    AlunnoDTO toDto (Alunno alunno);

    List<Alunno> toEntity(List<AlunnoDTO> alunnoDTOs);

    List<AlunnoDTO> toDto(List<Alunno> alunni);

    AlunnoWithoutCorsiDTO toDtoWithoutCorsi(Alunno alunno);

    List<AlunnoWithoutCorsiDTO> toDtoWithoutCorsi(List<Alunno> alunni);


    void updateAlunno(AlunnoDTO alunnoDto, @MappingTarget Alunno alunno);

//    Alunno toEntityWithoutCorsi(AlunnoWithoutCorsiDTO alunnoWithoutCorsiDTO);
//
//    Alunno toEntity(AlunnoWithoutCorsiDTO alunnoWithoutCorsiDto);

}
