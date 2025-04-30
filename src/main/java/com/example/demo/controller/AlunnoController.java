package com.example.demo.controller;

import com.example.demo.entity.Alunno;
import com.example.demo.service.AlunnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;

    @GetMapping("/lista")
    public String list(Model model){
        List<Alunno> alunni = new ArrayList<>();
        alunni = alunnoService.findAll();
        System.out.println(alunni);
        model.addAttribute("alunni", alunni);
        return "list-alunni";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model){
        model.addAttribute("alunno", new Alunno());
        return "form-alunno";
    }

    @PostMapping("/add")
    public  String create(@ModelAttribute("alunno") Alunno alunno, BindingResult br){
        if (br.hasErrors()) return "form-alunno";
        alunnoService.save(alunno);
        return "redirect:/alunni/lista";
    }

    @GetMapping("/{id}/edit")
    public  String showEdit(@PathVariable Long id, Model model){
        model.addAttribute("alunno", alunnoService.getById(id));
        return "/form-alunno";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("alunno") Alunno alunno, BindingResult br){
        if (br.hasErrors()) return "form-alunno";
        alunno.setId(id);
        alunnoService.save(alunno);
        return "redirect:/alunni";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        alunnoService.delete(id);
        return "redirect:/alunni/lista";
    }
}
