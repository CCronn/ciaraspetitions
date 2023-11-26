package com.example.ciaraspetitions.controller;

import com.example.ciaraspetitions.model.*;
import com.example.ciaraspetitions.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;
@RestController
@Controller
@RequestMapping("/petitions")
public class PetitionController {
    private final PetitionService petitionService;

    @Autowired
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @PostMapping("/submit")
    public RedirectView submitPetition(@ModelAttribute Petition petition, RedirectAttributes attributes) {
        System.out.println("Received petition data: " + petition);
        Petition createdPetition = petitionService.createPetition(petition);

        attributes.addFlashAttribute("id", createdPetition.getPetition_id());
        return new RedirectView("/petition_detail.html?id=" + createdPetition.getPetition_id(), true);
    }
}
