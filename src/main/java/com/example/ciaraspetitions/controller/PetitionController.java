package com.example.ciaraspetitions.controller;

import com.example.ciaraspetitions.exception.*;
import com.example.ciaraspetitions.model.*;
import com.example.ciaraspetitions.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return new RedirectView("ciaraspetitions/petition_detail.html?id=" + createdPetition.getPetition_id(), true);
    }

    @GetMapping
    public ResponseEntity<List<Petition>> getAllPetitions() {
        List<Petition> allPetitions = petitionService.getAllPetitions();
        return ResponseEntity.ok(allPetitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Petition> getPetitionById(@PathVariable Long id) {
        try {
            Petition petition = petitionService.getPetitionEntityById(id);
            return ResponseEntity.ok(petition);
        } catch (PetitionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
