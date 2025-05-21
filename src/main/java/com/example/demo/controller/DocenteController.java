package com.example.demo.controller;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @Autowired
    CorsoService corsoService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {

        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findAll();
        model.addAttribute("docenti",  docenteService.findAll());
        model.addAttribute("corsi", corsi);
        model.addAttribute("docenteFiltro", new DocenteDTO());
        model.addAttribute("isSearch", false);
        return "list-docenti";
    }
    @GetMapping("/list-docente-by-name")
    public String getDocenteByName(@ModelAttribute("docenteFiltro") Docente docenteFiltro, Model model){
        List<DocenteDTO> docenti = new ArrayList<>();

        docenti =  docenteService.findDocenteByName(docenteFiltro.getNome());
        model.addAttribute("docenti", docenti);
        model.addAttribute("docenteFiltro", docenteFiltro);
        model.addAttribute("isSearch", true);
        return "list-docenti";
    }
    @GetMapping("/list-docente-by-surname")
    public String getDocenteBySurname(@ModelAttribute("docenteFiltro") Docente docenteFiltro, Model model){
        List<DocenteDTO> docenti = new ArrayList<>();
        docenti =  docenteService.findDocenteBySurname(docenteFiltro.getCognome());
        model.addAttribute("docenti", docenti);
        model.addAttribute("docenteFiltro", docenteFiltro);
        model.addAttribute("isSearch", true);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteDTO());
        model.addAttribute("corsi", corsoService.findAll());
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping("/add")
    public String create(@ModelAttribute("docente") DocenteDTO docenteDto,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docenteDto);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("docente", docenteService.get(id));
        model.addAttribute("corsi", corsoService.findAll());
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") DocenteDTO docenteDto,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteDto.setId(id);
        docenteService.save(docenteDto);
        return "redirect:/docenti";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }








}

