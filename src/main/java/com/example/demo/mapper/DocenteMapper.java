package com.example.demo.mapper;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocenteMapper {

    public DocenteDTO convertFromEntityToDto(Docente docente){
        if (docente == null) {
            return null; // or create empty DTO, or throw, depending on your needs
        }
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setDataDiNascita(docente.getDataDiNascita());
        if (docente.getCorsi() != null && !docente.getCorsi().isEmpty()){
            docenteDTO.setCorsoIds(docente.getCorsi().stream()
                    .map(Corso::getId)
                    .collect(Collectors.toList()));
            docenteDTO.setCorsi(convertFromEntityToDtoCorsi(docente.getCorsi()));

        }
        return docenteDTO;
    }
    public List<CorsoDTO> convertFromEntityToDtoCorsi(List<Corso> corsi){
        List<CorsoDTO> corsiDto = new ArrayList<>();

        for (Corso corso : corsi ){
            CorsoDTO corsoDto = new CorsoDTO();
            corsoDto.setId(corso.getId());
            corsoDto.setNome(corso.getNome());
            corsoDto.setOre(corso.getOre());
            corsoDto.setAnnoAccademico(corso.getAnnoAccademico());
            corsiDto.add(corsoDto);
        }
        return corsiDto;
    }
    private List<Corso> convertFromDtoToEntityCorsi(List<CorsoDTO> corsiDto){
        List<Corso> corsi = new ArrayList<>();
        System.out.println(corsiDto);

        for (CorsoDTO corsoDto : corsiDto){
            Corso corso = new Corso();
            corso.setId(corsoDto.getId());
            corso.setNome(corsoDto.getNome());
            corso.setOre(corsoDto.getOre());
            corso.setAnnoAccademico(corsoDto.getAnnoAccademico());
            corsi.add(corso);
        }

        return corsi;
    }
    public void updateFromDtoToEntity(DocenteDTO docenteDto, Docente docente){
        docente.setId(docenteDto.getId());
        docente.setNome(docenteDto.getNome());
        docente.setCognome(docenteDto.getCognome());
        docente.setDataDiNascita(docenteDto.getDataDiNascita());
    }

    public Docente convertFromDtoToEntity(DocenteDTO docenteDto){
        if (docenteDto == null) {
            return null; // or create empty DTO, or throw, depending on your needs
        }
        Docente docente = new Docente();
        docente.setId(docenteDto.getId());
        docente.setNome(docenteDto.getNome());
        docente.setCognome(docenteDto.getCognome());
        docente.setDataDiNascita(docenteDto.getDataDiNascita());
        //docente.setCorsi(convertFromDtoToEntityCorsi(docenteDto.getCorsi()));
        return docente;
    }
}
