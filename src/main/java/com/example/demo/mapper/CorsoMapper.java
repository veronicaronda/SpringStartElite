package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorsoMapper {
    @Autowired
    private DocenteMapper docenteMapper;

    @Autowired
    @Lazy
    AlunnoMapper alunnoMapper;

    @Autowired
    public CorsoMapper(AlunnoMapper alunnoMapper) {
        this.alunnoMapper = alunnoMapper;
    }


    public CorsoDTO convertFromEntityToDto(Corso corso){
        CorsoDTO corsoDto = new CorsoDTO();
        corsoDto.setId(corso.getId());
        corsoDto.setNome(corso.getNome());
        corsoDto.setOre(corso.getOre());
        corsoDto.setAnnoAccademico(corso.getAnnoAccademico());
        corsoDto.setDocente(docenteMapper.convertFromEntityToDto(corso.getDocente()));
        if (corso.getAlunni() != null && !corso.getAlunni().isEmpty()){
            corsoDto.setAlunniIds(corso.getAlunni().stream()
                    .map(Alunno::getId)
                    .collect(Collectors.toList()));
        }

        return corsoDto;
    }
    public CorsoDTO convertFromEntityToDtoWithAlunni(Corso corso){
        CorsoDTO corsoDto = convertFromEntityToDto(corso);
        corsoDto.setAlunni(
                corso.getAlunni().stream()
                        .map(this::convertFtomDtoToEntityWithAlunni)
                        .collect(Collectors.toList())
        );
        return corsoDto;

    }
    private AlunnoDTO convertFtomDtoToEntityWithAlunni(Alunno alunno){
        AlunnoDTO alunnoDto = new AlunnoDTO();
        alunnoDto.setId(alunno.getId());
        alunnoDto.setNome(alunno.getNome());
        alunnoDto.setCognome(alunno.getCognome());
        return alunnoDto;
    }
    public void updateFromDtoToEntity(CorsoDTO corsoDto, Corso corso){
        corso.setId(corsoDto.getId());
        corso.setNome(corsoDto.getNome());
        corso.setOre(corsoDto.getOre());
        corso.setAnnoAccademico(corsoDto.getAnnoAccademico());
        corso.setDocente(docenteMapper.convertFromDtoToEntity(corsoDto.getDocente()));
    }
    public Corso convertFromDtoToEntity(CorsoDTO corsoDto){
        Corso corso = new Corso();
        corso.setId(corsoDto.getId());
        corso.setNome(corsoDto.getNome());
        corso.setOre(corsoDto.getOre());
        corso.setAnnoAccademico(corsoDto.getAnnoAccademico());
        corso.setDocente(docenteMapper.convertFromDtoToEntity(corsoDto.getDocente()));
        return corso;
    }
}
