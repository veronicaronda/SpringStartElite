package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.repository.CorsoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunnoService {
    @Autowired
    AlunnoRepository alunnoRepository;

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    AlunnoMapper alunnoMapper;




    public  List<AlunnoDTO> findAll(){
        return alunnoMapper.toDto(alunnoRepository.findAll());
    }

    public AlunnoDTO getById(Long id){
        return alunnoMapper.toDto(alunnoRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<AlunnoDTO> getAlunnoByNameOrSurname(String keyword){
//        List<Alunno> alunni = new ArrayList<>();
//        alunni = alunnoRepository.getAlunniByNameOrSurname(keyword);

        return alunnoRepository.getAlunniByNameOrSurname(keyword).stream().map(alunnoMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<AlunnoDTO> getAlunniPassed(){
//        List<Alunno> alunni = new ArrayList<>();
//        alunni = alunnoRepository.getAlunniPassed();
        return alunnoRepository.getAlunniPassed().stream().map(alunnoMapper::toDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public AlunnoDTO save(AlunnoDTO alunnoDto){

        List<Corso> fullCorsi = alunnoDto.getCorsiIds() != null
                ? corsoRepository.findAllById(alunnoDto.getCorsiIds())
                : new ArrayList<>();

        if (alunnoDto.getId() != null) {
            Alunno alunno = alunnoRepository.findById(alunnoDto.getId())
                    .orElseThrow(EntityNotFoundException::new);
           alunnoMapper.updateAlunno(alunnoDto, alunno);
            alunno.setCorsi(fullCorsi); // ✅ hydration handled here
            alunnoRepository.save(alunno);
            return alunnoMapper.toDto(alunno);
        } else {
            Alunno alunno = alunnoMapper.toEntity(alunnoDto);


            alunno.setCorsi(fullCorsi);
            fullCorsi.forEach(corso -> {
                if (!corso.getAlunni().contains(alunno)) {
                    corso.getAlunni().add(alunno);
                    corsoRepository.save(corso);
                }
            });

            alunnoRepository.save(alunno);
            return alunnoMapper.toDto(alunno);
        }
    }

    public void deleteAlunnoFromCorso(Long id, Long corsoId){
        Alunno alunno = alunnoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Corso corso = corsoRepository.findById(corsoId).orElseThrow(EntityNotFoundException::new);

        corso.getAlunni().remove(alunno);
        alunno.getCorsi().remove(corso);

        alunnoRepository.save(alunno);
        corsoRepository.save(corso);


    }

    public void delete(Long id){
        Alunno alunno = alunnoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        for (Corso corso : alunno.getCorsi()){
            corso.getAlunni().remove(alunno);
        }
        alunno.getCorsi().clear();
        alunnoRepository.save(alunno);
        alunnoRepository.deleteById(id);
    }
}