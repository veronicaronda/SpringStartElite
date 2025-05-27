package com.example.demo.controller;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.service.AlunnoService;
import com.example.demo.service.CorsoService;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;

    @Autowired
    CorsoService corsoService;

    @GetMapping("/lista")
    public List<AlunnoDTO> list(){
        return alunnoService.findAll();
    }

    @GetMapping("/{id}")
    public AlunnoDTO getById(@PathVariable Long id){
        return alunnoService.getById(id);
    }



//    @GetMapping("/lista-alunni-per-nome-cognome")
//    //    MODEL AND VIEW
//    public ModelAndView listByNameOrSurname( @RequestParam("keyword") String keyword){
//        List<AlunnoDTO> alunni = new ArrayList<>();
//        alunni = alunnoService.getAlunnoByNameOrSurname(keyword);
//        ModelAndView mnv = new ModelAndView("list-alunni");
//        mnv.addObject("alunni", alunni);
//        mnv.addObject("keyword", keyword);
//        mnv.addObject("alunno", new Alunno());
//        mnv.addObject("isSearch", true);
//        mnv.addObject("isPassed", false);
//
//        return mnv;
//    }
//
//    @GetMapping("/lista-alunni-promossi")
//    public ModelAndView listAlunniPassed(){
//        List<AlunnoDTO> alunni = new ArrayList<>();
//        alunni = alunnoService.getAlunniPassed();
//        ModelAndView mnv = new ModelAndView("list-alunni","alunni", alunni );
//        mnv.addObject("isPassed", true);
//
//        return mnv;
//    }

    @PostMapping("/add")
    public AlunnoDTO create(@RequestBody AlunnoDTO alunnoDto){
        return alunnoService.save(alunnoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunnoDTO> update(@PathVariable Long id,
                                            @RequestBody AlunnoDTO alunnoDto) {
        try{
            alunnoDto.setId(id);
            return ResponseEntity.ok(alunnoService.save(alunnoDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        alunnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete/{corsoId}")
    public ResponseEntity<Void> deleteAlunnoFromCorso(@PathVariable Long id,
                                                      @PathVariable Long corsoId){
        alunnoService.deleteAlunnoFromCorso(id, corsoId);
        return ResponseEntity.noContent().build();

    }

}