package com.example.demo.service;

import com.example.demo.entity.Alunno;
import com.example.demo.repository.AlunnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunnoService {
    @Autowired
    AlunnoRepository alunnoRepository;

    public List<Alunno> findAll(){
        return alunnoRepository.findAll();
    }

    public Alunno getById(Long id){
        return alunnoRepository.findById(id).orElseThrow();
    }

    public Alunno save(Alunno a){
        return alunnoRepository.save(a);
    }

    public  void delete(Long id){
        alunnoRepository.deleteById(id);
    }
}
