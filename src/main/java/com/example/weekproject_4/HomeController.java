package com.example.weekproject_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    BankRepository bankRepository;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("courses", bankRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("course", new Bank());
        return "bankform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Bank Bank, BindingResult result){
        if (result.hasErrors()){
            return "bankform";
        }
        bankRepository.save(Bank);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){
        model.addAttribute("course", bankRepository.findById(id).get());
        return "show";
    }


}