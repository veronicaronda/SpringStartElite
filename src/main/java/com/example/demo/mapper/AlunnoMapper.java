package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlunnoMapper {

    public AlunnoDTO convertFromEntityToDto(Alunno alunno) {

        AlunnoDTO alunnoDto = new AlunnoDTO();
        alunnoDto.setId(alunno.getId());
        alunnoDto.setNome(alunno.getNome());
        alunnoDto.setCognome(alunno.getCognome());
        alunnoDto.setDataDiNascita(alunno.getDataDiNascita());
        alunnoDto.setCittaDiResidenza(alunno.getCittaDiResidenza());
        alunnoDto.setVoto(alunno.getVoto());
        if (alunno.getCorsi() != null && !alunno.getCorsi().isEmpty()) {
            // 1. For form binding
            alunnoDto.setCorsiIds(alunno.getCorsi().stream()
                    .map(Corso::getId)
                    .collect(Collectors.toList()));

            alunnoDto.setCorsi(convertFromEntityToDtoCorsi(alunno.getCorsi()));
        }else{
            alunnoDto.setCorsi(null);
        }

        return alunnoDto;

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
    public Alunno convertFromDtoToEntity(AlunnoDTO alunnoDto){
        Alunno alunno = new Alunno();
        alunno.setId(alunnoDto.getId());
        alunno.setNome(alunnoDto.getNome());
        alunno.setCognome(alunnoDto.getCognome());
        alunno.setVoto(alunnoDto.getVoto());
        alunno.setCittaDiResidenza(alunnoDto.getCittaDiResidenza());
        alunno.setDataDiNascita(alunnoDto.getDataDiNascita());
        return alunno;
    }

    public void updateFromDtoToEntity(AlunnoDTO alunnoDto, Alunno alunno){
        alunno.setNome(alunnoDto.getNome());
        alunno.setCognome(alunnoDto.getCognome());
        alunno.setVoto(alunnoDto.getVoto());
        alunno.setCittaDiResidenza(alunnoDto.getCittaDiResidenza());
        alunno.setDataDiNascita(alunnoDto.getDataDiNascita());

    }
}
