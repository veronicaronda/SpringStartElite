package com.example.demo.controller;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.service.AlunnoService;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;
    @Autowired
    DocenteService docenteService;
    @Autowired
    AlunnoService alunnoService;

    @GetMapping("/lista")
    public List<CorsoDTO> list() {
        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findAll();
        return corsi;
    }


    @PostMapping("/add")
    public CorsoDTO create(@RequestBody CorsoDTO corsoDto){
        return corsoService.save(corsoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> update(@PathVariable Long id,
                                             @RequestBody CorsoDTO corsoDto) {
        try{
            corsoDto.setId(id);
            return ResponseEntity.ok(corsoService.save(corsoDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        corsoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/delete/{alunnoId}")
    public ResponseEntity<Void> deleteCorsoFromAlunno(@PathVariable Long id,
                                              @PathVariable Long alunnoId){
        corsoService.deleteCorsoFromAlunno(id, alunnoId);
        return ResponseEntity.noContent().build();

    }

    //    @GetMapping("/lista-corsi-per-nome")
//    public String getCorsoByName(@ModelAttribute("corso") Corso corso, Model model){
//        List<CorsoDTO> corsi = new ArrayList<>();
//        corsi = corsoService.findCorsoByName(corso.getNome());
//        model.addAttribute("corsi", corsi);
//        model.addAttribute("isSearch", true);
//        return "list-corsi";
//    }
//    @GetMapping("/lista-corsi-per-anno-accademico")
//    public String getCorsoByYear(@ModelAttribute("corso") CorsoDTO corso, Model model){
//        List<CorsoDTO> corsi = new ArrayList<>();
//        corsi = corsoService.findCorsoByYear(corso.getAnnoAccademico());
//        model.addAttribute("corsi", corsi);
//        model.addAttribute("isSearch", true);
//        return "list-corsi";
//    }
//    @GetMapping("/lista-corsi-per-docente")
//    public String getCorsoByTeacher(@RequestParam("keyword") String keyword, Model model){
//        List<CorsoDTO> corsi = new ArrayList<>();
//        corsi = corsoService.findCorsoByTeacher(keyword);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("corso", new CorsoDTO());
//        model.addAttribute("corsi",corsi);
//        model.addAttribute("isSearch", true);
//        return "list-corsi";
//    }


}
