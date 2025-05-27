package com.example.demo.controller;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @Autowired
    CorsoService corsoService;



    // LISTA
    @GetMapping("/lista")
    public List<DocenteDTO> list() {
//        List<DocenteDTO> docenti = new ArrayList<>();
//        docenti = docenteService.findAll();
        return docenteService.findAll();
    }

//    @PostMapping("/search")
//    public ResponseEntity<ResponsePageDTO<List<DocenteDTO>>> getObjectType(@RequestParam(defaultValue = "0") Integer page,
//                                                                           @RequestParam(defaultValue = "30") Integer size,
//                                                                           @RequestParam(defaultValue = "") List<String> sortList,
//                                                                           @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder,
//                                                                           @RequestBody(required = false) List<FilterDTO> filterDtoList) {
//        ResponsePageDTO<List<DocenteDTO>> responsePageDto = service.findByCustomFilter(filterDtoList, page, size, sortList, sortOrder, mapper::toDto);
//        return new ResponseEntity<>(responsePageDto, HttpStatus.OK);
//    }

//    @GetMapping("/list-docente-by-name/{docenteNome}")
//    public List<DocenteDTO> getDocenteByName(@PathVariable("docenteNome") String docenteNome){
//        List<DocenteDTO> docenti = new ArrayList<>();
//
//        docenti =  docenteService.findDocenteByName(docenteNome);
//
//        return docenti;
//    }
//    @GetMapping("/list-docente-by-surname/{docenteCognome}")
//    public List<DocenteDTO> getDocenteBySurname(@PathVariable("docenteCognome") String docenteCognome){
//        List<DocenteDTO> docenti = new ArrayList<>();
//        docenti =  docenteService.findDocenteBySurname(docenteCognome);
//
//        return docenti;
//    }


    // SALVA NUOVO
    @PostMapping("/add")
    public DocenteDTO create(@RequestBody DocenteDTO docenteDto) {
        return docenteService.save(docenteDto);
    }


    // AGGIORNA
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> update(@PathVariable Long id,
                                             @RequestBody DocenteDTO docenteDto) {
        try{
            docenteDto.setId(id);
            DocenteDTO docentDto = docenteService.save(docenteDto);
            return ResponseEntity.ok(docentDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }

    // DELETE
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void>  delete(@PathVariable Long id) {
        docenteService.delete(id);
        return  ResponseEntity.noContent().build();
    }








}