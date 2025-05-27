package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.AlunnoMapper;
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
    private AlunnoRepository alunnoRepository;
    @Autowired
    DocenteRepository docenteRepository;
    @Autowired
    CorsoMapper corsoMapper;
    @Autowired
    AlunnoMapper alunnoMapper;

    public List<CorsoDTO> findAll(){
        List<Corso> corsi = corsoRepository.findAll();
        List<CorsoDTO> corsiDtos = new ArrayList<>();
        for(Corso corso : corsi) {
            CorsoDTO corsoDto = corsoMapper.toDto(corso);

            if (corso.getAlunni() != null) {
                List<AlunnoWithoutCorsiDTO> alunniDTO = alunnoMapper.toDtoWithoutCorsi(corso.getAlunni());
                corsoDto.setAlunni(alunniDTO);
            } else {
                corsoDto.setAlunni(null);
            }
            corsiDtos.add(corsoDto);
        }
        return corsiDtos;

    }

    public List<CorsoDTO> findAllById(List<Long> courseIds){
//        List<Corso> corsi = new ArrayList<>();
//        corsi = corsoRepository.findAllById(courseIds);
        return corsoRepository.findAllById(courseIds).stream().map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findCorsoByName(String nome){
//        List<Corso> corsi = new ArrayList<>();
//        corsi = corsoRepository.findCorsoByName(nome);
        return corsoRepository.findCorsoByName(nome).stream().map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findCorsoByYear(int annoAccademico){
//        List<Corso> corsi = new ArrayList<>();
//        corsi = corsoRepository.findCorsoByYear(annoAccademico);
        return corsoRepository.findCorsoByYear(annoAccademico).stream().map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findCorsoByTeacher(String keyword){
//        List<Corso> corsi = new ArrayList<>();
//        corsi = corsoRepository.findCorsoByTeacher(keyword);
        return corsoRepository.findCorsoByTeacher(keyword)
                .stream().map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }

    public CorsoDTO save(CorsoDTO corsoDto) {
        List<Alunno> fullAlunni = corsoDto.getAlunniIds() != null
                ? alunnoRepository.findAllById(corsoDto.getAlunniIds())
                : new ArrayList<>();

        Docente docente = docenteRepository.findById(corsoDto.getDocente().getId()).orElseThrow(EntityNotFoundException::new);

        if (corsoDto.getId() != null) {
            Corso corso = corsoRepository.findById(corsoDto.getId())
                    .orElseThrow(EntityNotFoundException::new);
            corsoMapper.updateCorso(corsoDto, corso);

            corso.setDocente(docente);
            corso.setAlunni(fullAlunni); // ✅ hydration handled here

            // Sync alunni
            fullAlunni.forEach(alunno -> {
                if (!alunno.getCorsi().contains(corso)) {
                    alunno.getCorsi().add(corso);
                }
            });
            corsoRepository.save(corso);
            return corsoMapper.toDto(corso);

        } else {
            Corso corso = corsoMapper.toEntity(corsoDto);
            corso.setDocente(docente);
            corso.setAlunni(fullAlunni); // ✅ hydration handled here
            // Sync alunni
            fullAlunni.forEach(alunno -> {
                if (!alunno.getCorsi().contains(corso)) {
                    alunno.getCorsi().add(corso);
                }
            });
            corsoRepository.save(corso);
            return corsoMapper.toDto(corso);
        }
    }

    public CorsoDTO getById(Long id){

        return corsoMapper.toDto(corsoRepository.findById(id).orElseThrow());
    }

    public void deleteCorsoFromAlunno(Long id, Long alunnoId) {
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