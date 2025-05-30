package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorsoService {
    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    AlunnoRepository alunnoRepository;
    @Autowired
    DocenteRepository docenteRepository;
    @Autowired
    CorsoMapper corsoMapper;

    public List<CorsoDTO> findAll(){
        List<Corso> corsi = new ArrayList<>();
        corsi = corsoRepository.getCorsiWithAlunniOrderBy();
        return corsi.stream().map(corsoMapper::convertFromEntityToDtoWithAlunni)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findAllById(List<Long> courseIds){
        List<Corso> corsi = new ArrayList<>();
        corsi = corsoRepository.findAllById(courseIds);
        return corsi.stream().map(corsoMapper::convertFromEntityToDtoWithAlunni)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findCorsoByName(String nome){
        List<Corso> corsi = new ArrayList<>();
        corsi = corsoRepository.findCorsoByName(nome);
        return corsi.stream().map(corsoMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findCorsoByYear(int annoAccademico){
        List<Corso> corsi = new ArrayList<>();
        corsi = corsoRepository.findCorsoByYear(annoAccademico);
        return corsi.stream().map(corsoMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findCorsoByTeacher(String keyword){
        List<Corso> corsi = new ArrayList<>();
        corsi = corsoRepository.findCorsoByTeacher(keyword);
        return corsi.stream().map(corsoMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    public Corso save(CorsoDTO corsoDto, Long docenteId) {
        List<Alunno> fullAlunni = corsoDto.getAlunniIds() != null
                ? alunnoRepository.findAllById(corsoDto.getAlunniIds())
                : new ArrayList<>();

        Docente docente = docenteRepository.findById(docenteId).orElseThrow(EntityNotFoundException::new);

        if (corsoDto.getId() != null) {
            Corso corso = corsoRepository.findById(corsoDto.getId())
                    .orElseThrow(EntityNotFoundException::new);
            corsoMapper.updateFromDtoToEntity(corsoDto, corso);

            corso.setDocente(docente);
            corso.setAlunni(fullAlunni); // ✅ hydration handled here

            // Sync alunni
            fullAlunni.forEach(alunno -> {
                if (!alunno.getCorsi().contains(corso)) {
                    alunno.getCorsi().add(corso);
                }
            });
            return corsoRepository.save(corso);
        } else {
            Corso corso = corsoMapper.convertFromDtoToEntity(corsoDto);
            corso.setDocente(docente);
            corso.setAlunni(fullAlunni); // ✅ hydration handled here
            // Sync alunni
            fullAlunni.forEach(alunno -> {
                if (!alunno.getCorsi().contains(corso)) {
                    alunno.getCorsi().add(corso);
                }
            });
            return corsoRepository.save(corso);
        }
    }

    public CorsoDTO getById(Long id){
        Corso corso = new Corso();
        corso = corsoRepository.findById(id).orElseThrow();
        return corsoMapper.convertFromEntityToDtoWithAlunni(corso);
    }

    public void deleteCorsoFromAlunno(Long id, Long alunnoId){
        Alunno alunno = alunnoRepository.findById(alunnoId).orElseThrow(EntityNotFoundException::new);
        Corso corso = corsoRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        corso.getAlunni().remove(alunno);
        alunno.getCorsi().remove(corso);

        alunnoRepository.save(alunno);
        corsoRepository.save(corso);


    }

    public void  delete(Long id){
        Corso corso = corsoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        for (Alunno alunno : corso.getAlunni()){
            alunno.getCorsi().remove(corso);
        }
        corso.getAlunni().clear();
        corsoRepository.save(corso);
        corsoRepository.deleteById(id);
    }
}
