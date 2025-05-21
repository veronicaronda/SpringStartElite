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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;
    @Autowired
    DocenteService docenteService;
    @Autowired
    AlunnoService alunnoService;

    @GetMapping("/lista")
    public String list(Model model) {
        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findAll();

        List<DocenteDTO> docenti = new ArrayList<>();
        docenti = docenteService.orderDocenteBySurname();

        model.addAttribute("corsi", corsi);
        model.addAttribute("corso", new Corso());
        model.addAttribute("docenti", docenti);
        return "list-corsi";
    }

    @GetMapping("/lista-corsi-per-nome")
    public String getCorsoByName(@ModelAttribute("corso") Corso corso, Model model){
        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findCorsoByName(corso.getNome());
        model.addAttribute("corsi", corsi);
        model.addAttribute("isSearch", true);
        return "list-corsi";
    }
    @GetMapping("/lista-corsi-per-anno-accademico")
    public String getCorsoByYear(@ModelAttribute("corso") CorsoDTO corso, Model model){
        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findCorsoByYear(corso.getAnnoAccademico());
        model.addAttribute("corsi", corsi);
        model.addAttribute("isSearch", true);
        return "list-corsi";
    }
    @GetMapping("/lista-corsi-per-docente")
    public String getCorsoByTeacher(@RequestParam("keyword") String keyword, Model model){
        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findCorsoByTeacher(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("corso", new CorsoDTO());
        model.addAttribute("corsi",corsi);
        model.addAttribute("isSearch", true);
        return "list-corsi";
    }
    @GetMapping("/nuovo")
    public String showAdd(Model model){
        model.addAttribute("corso", new CorsoDTO());
        List<DocenteDTO> docenti = new ArrayList<>();
        docenti = docenteService.findAll();
        model.addAttribute("docenti", docenti);
        List<AlunnoDTO> alunni = new ArrayList<>();
        alunni = alunnoService.findAll();
        model.addAttribute("alunni", alunni);
        return "form-corso";
    }

    @PostMapping("/add")
    public String create(@RequestParam("docenteId") Long docenteId,
                         @ModelAttribute("corso") CorsoDTO corso, Model model, BindingResult br){
        if (br.hasErrors()) {
            model.addAttribute("alunni", alunnoService.findAllWithCorsi());
            return "form-corso";
        }
        corsoService.save(corso, docenteId);
        return "redirect:/corsi/lista";
    }

    @GetMapping("/{id}/modifica")
    public String showEdit(@PathVariable Long id, Model model){
        model.addAttribute("corso", corsoService.getById(id));
        List<DocenteDTO> docenti = new ArrayList<>();
        docenti = docenteService.findAll();
        model.addAttribute("docenti", docenti);
        List<AlunnoDTO> alunni = new ArrayList<>();
        alunni = alunnoService.findAllWithCorsi();
        model.addAttribute("alunni", alunni);
        return "form-corso";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        corsoService.delete(id);
        return "redirect:/corsi/lista";
    }
    @GetMapping("/{id}/delete/{alunnoId}")
    public ModelAndView deleteCorsoFromAlunno(@PathVariable Long id,
                                              @PathVariable Long alunnoId){
        corsoService.deleteCorsoFromAlunno(id, alunnoId);
        return new ModelAndView("redirect:/corsi/lista");

    }


}
