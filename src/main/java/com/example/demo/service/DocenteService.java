package com.example.demo.service;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    CorsoRepository corsoRepository;
    @Autowired
    DocenteMapper docenteMapper;

    public List<DocenteDTO> findAll() {
        List<Docente> docenti = new ArrayList<>();
        docenti = docenteRepository.findAllWithCorsiOrderBy();
        System.out.println(docenti);
        return docenti.stream().map(docenteMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
//        return docenti.stream()
//                .sorted(Comparator.comparing(Docente::getId))
//                .collect(Collectors.toList());
    }
    public List<DocenteDTO> findDocenteByName(String docenteNome){
        List<Docente> docenti = new ArrayList<>();
        docenti = docenteRepository.findDocenteByName(docenteNome);
        return docenti.stream().map(docenteMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<DocenteDTO> findDocenteBySurname(String docenteCognome){
        List<Docente> docenti = new ArrayList<>();
        docenti= docenteRepository.findDocenteBySurname(docenteCognome);
        return docenti.stream().map(docenteMapper::convertFromEntityToDto)
                .collect(Collectors.toList());

    }

    public List<DocenteDTO> orderDocenteBySurname(){
        List<Docente> docenti = new ArrayList<>();
        docenti = docenteRepository.findAllByOrderByCognomeAsc();
        return docenti.stream().map(docenteMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    public DocenteDTO get(Long id) {
        Docente docente = new Docente();
        docente = docenteRepository.findById(id).orElseThrow();
        return docenteMapper.convertFromEntityToDto(docente);
    }

    public Docente save(DocenteDTO docenteDto) {
        List<Corso> fullCorsi = docenteDto.getCorsoIds() != null
        ? corsoRepository.findAllById(docenteDto.getCorsoIds())
        : new ArrayList<>();
        fullCorsi.forEach(corso -> System.out.println(corso.getNome()));
        if (docenteDto.getId() != null){

            Docente docente = docenteRepository.findById(docenteDto.getId())
                    .orElseThrow(EntityNotFoundException::new);

            docenteMapper.updateFromDtoToEntity(docenteDto, docente);

            docente.setCorsi(fullCorsi);

            for (Corso corso : fullCorsi) {
                corso.setDocente(docente);
            }
            return docenteRepository.save(docente);
        }else {
            Docente docente = docenteMapper.convertFromDtoToEntity(docenteDto);
            docente.setCorsi(fullCorsi);
            for (Corso corso : fullCorsi) {
                corso.setDocente(docente);
            }
//            fullCorsi.stream().map(corso -> {corso.setDocente(docente) return corso});
            return docenteRepository.save(docente);
        }
    }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        for (Corso corso : docente.getCorsi()){
            if(docente.getId() == corso.getDocente().getId() ){
                corso.setDocente(null);
            }

        }
        docenteRepository.save(docente);
        docenteRepository.deleteById(id);
    }
}
