package com.example.demo.controller;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.service.AlunnoService;
import com.example.demo.service.CorsoService;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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

@Controller
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;

    @Autowired
    CorsoService corsoService;

    @GetMapping("/lista")
//    MODEL AND VIEW
    public ModelAndView list(){

        List<AlunnoDTO> alunni = new ArrayList<>();
        alunni = alunnoService.findAllWithCorsi();
        System.out.println(alunni);
        List<CorsoDTO> corsi = new ArrayList<>();
        corsi = corsoService.findAll();


        ModelAndView mnv = new ModelAndView("list-alunni");
        mnv.addObject("alunni", alunni);
        mnv.addObject("corsi", corsi );


        return mnv;
    }

//    MODEL
//    public String list(Model model){
//        List<Alunno> alunni = new ArrayList<>();
//        alunni = alunnoService.findAll();
//        System.out.println(alunni);
//        model.addAttribute("alunni", alunni);
//        return "list-alunni";
//    }

    @GetMapping("/nuovo")
//    MODEL AND VIEW
    public ModelAndView showAdd(){
        ModelAndView mnv = new ModelAndView("form-alunno");
        mnv.addObject("alunno", new AlunnoDTO());
        mnv.addObject("corsi", corsoService.findAll());
        return mnv;
    }
//    public String showAdd(Model model){
//        model.addAttribute("alunno", new Alunno());
//        return "form-alunno";
//    }

    @GetMapping("/lista-alunni-per-nome-cognome")
    //    MODEL AND VIEW
    public ModelAndView listByNameOrSurname( @RequestParam("keyword") String keyword){
        List<AlunnoDTO> alunni = new ArrayList<>();
        alunni = alunnoService.getAlunnoByNameOrSurname(keyword);
        ModelAndView mnv = new ModelAndView("list-alunni");
        mnv.addObject("alunni", alunni);
        mnv.addObject("keyword", keyword);
        mnv.addObject("alunno", new Alunno());
        mnv.addObject("isSearch", true);
        mnv.addObject("isPassed", false);

        return mnv;
    }

    @GetMapping("/lista-alunni-promossi")
    public ModelAndView listAlunniPassed(){
        List<AlunnoDTO> alunni = new ArrayList<>();
        alunni = alunnoService.getAlunniPassed();
        ModelAndView mnv = new ModelAndView("list-alunni","alunni", alunni );
        mnv.addObject("isPassed", true);

        return mnv;
    }

    @PostMapping("/add")
    //MODEL AND VIEW
    public ModelAndView create(@ModelAttribute("alunno") AlunnoDTO alunno, BindingResult br){
        ModelAndView mnv = new ModelAndView();
        if (br.hasErrors()) {
            mnv.setViewName("form-alunno");
            mnv.addObject("corsi", corsoService.findAll());
            return mnv;
        }

        alunnoService.save(alunno);
        return new ModelAndView("redirect:/alunni/lista");
    }
//    public  String create(@ModelAttribute("alunno") Alunno alunno, BindingResult br){
//        if (br.hasErrors()) return "form-alunno";
//        alunnoService.save(alunno);
//        return "redirect:/alunni/lista";
//    }

    @GetMapping("/{id}/edit")
    //MODEL AND VIEW
    public ModelAndView showEdit(@PathVariable Long id){
        ModelAndView mnv = new ModelAndView("form-alunno");
        mnv.addObject("alunno", alunnoService.getById(id));
        mnv.addObject("corsi", corsoService.findAll());
        return mnv;

    }
//    public  String showEdit(@PathVariable Long id, Model model){
//        model.addAttribute("alunno", alunnoService.getById(id));
//        return "/form-alunno";
//    }


//    public String update(@PathVariable Long id, @ModelAttribute("alunno") Alunno alunno, BindingResult br){
//        if (br.hasErrors()) return "form-alunno";
//        alunno.setId(id);
//        alunnoService.save(alunno);
//        return "redirect:/alunni";
//    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        alunnoService.delete(id);
        return new ModelAndView("redirect:/alunni/lista");

    }

    @GetMapping("/{id}/delete/{corsoId}")
    public ModelAndView deleteAlunnoFromCorso(@PathVariable Long id,
                                              @PathVariable Long corsoId){
        alunnoService.deleteAlunnoFromCorso(id, corsoId);
        return new ModelAndView("redirect:/alunni/lista");

    }
//    public String delete(@PathVariable Long id) {
//        alunnoService.delete(id);
//        return "redirect:/alunni/lista";
//    }
}
