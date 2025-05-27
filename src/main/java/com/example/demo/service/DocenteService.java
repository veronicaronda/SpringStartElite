package com.example.demo.service;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
//        List<Docente> docenti = new ArrayList<>();
//        docenti = docenteRepository.findAllWithCorsiOrderBy();
        return docenteRepository.findAll().stream().map(docenteMapper::toDto)
                .collect(Collectors.toList());
//        return docenti.stream()
//                .sorted(Comparator.comparing(Docente::getId))
//                .collect(Collectors.toList());
    }
    public List<DocenteDTO> findDocenteByName(String docenteNome){
//        List<Docente> docenti = new ArrayList<>();
//        docenti = docenteRepository.findDocenteByName(docenteNome);
        return docenteRepository.findDocenteByName(docenteNome).stream().map(docenteMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<DocenteDTO> findDocenteBySurname(String docenteCognome){
//        List<Docente> docenti = new ArrayList<>();
//        docenti= docenteRepository.findDocenteBySurname(docenteCognome);
        return docenteRepository.findDocenteBySurname(docenteCognome).stream().map(docenteMapper::toDto)
                .collect(Collectors.toList());

    }

    public List<DocenteDTO> orderDocenteBySurname(){
//        List<Docente> docenti = new ArrayList<>();
//        docenti = docenteRepository.findAllByOrderByCognomeAsc();
        return docenteRepository.findAllByOrderByCognomeAsc().stream().map(docenteMapper::toDto)
                .collect(Collectors.toList());
    }

    public DocenteDTO get(Long id) {
//        Docente docente = new Docente();
//        docente = docenteRepository.findById(id).orElseThrow();
        return docenteMapper.toDto(docenteRepository.findById(id).orElseThrow());
    }

    @Transactional
    public DocenteDTO save(DocenteDTO docenteDto) {

        List<Corso> fullCorsi = docenteDto.getCorsoIds() != null
                ? corsoRepository.findAllById(docenteDto.getCorsoIds())
                : new ArrayList<>();

        if (docenteDto.getId() != null){

            Docente docente = docenteRepository.findById(docenteDto.getId()).orElseThrow(EntityNotFoundException::new);



            docenteMapper.updateDocente(docenteDto, docente);


            for (Corso OldCorso : docente.getCorsi()) {
                if(!fullCorsi.contains(OldCorso)){
                    Corso corso = corsoRepository.findById(OldCorso.getId()).orElseThrow();
                    corso.setDocente(null);
                    corsoRepository.save(corso);
                }
            }
            for(Corso newCorso : fullCorsi){
                if (!docente.getCorsi().contains(newCorso)) {
                    Corso corso = corsoRepository.findById(newCorso.getId()).orElseThrow();
                    corso.setDocente(docente);
                    corsoRepository.save(corso);
                }
            }

            docente.setCorsi(fullCorsi);
            docenteRepository.save(docente);
            return docenteMapper.toDto(docente);
        }else {
            Docente docente = docenteMapper.toEntity(docenteDto);
            for (Corso corso : docente.getCorsi()) {

                Corso c = corsoRepository.findById(corso.getId()).orElseThrow();
                c.setDocente(docente);

            }
            docenteRepository.save(docente);
//            fullCorsi.stream().map(corso -> {corso.setDocente(docente) return corso});
            return docenteMapper.toDto(docente);
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