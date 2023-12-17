package com.example.ciaraspetitions.service;

import com.example.ciaraspetitions.exception.PetitionNotFoundException;
import com.example.ciaraspetitions.model.*;
import com.example.ciaraspetitions.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PetitionService {
    private final PetitionRepository petitionRepository;

    @Autowired
    public PetitionService(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    @Transactional
    public Petition createPetition(Petition petition) {
        // save the petition to the DB
        Petition createdPetition = petitionRepository.save(petition);
        return createdPetition;
    }


    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }


    @Transactional
    public Petition getPetitionEntityById(Long id) {
        // find petition by id else exception
        Petition petition = petitionRepository.findById(id)
                .orElseThrow(() -> new PetitionNotFoundException("Petition not found with id: " + id));

        return petition;
    }


    @Transactional
    public void signPetition(Long petitionId, Signer signerForm) {
        // first find the petition else exception
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new PetitionNotFoundException("Petition not found with id: " + petitionId));
        // create new signer and add details given
        Signer signer = new Signer();
        signer.setForename(signerForm.getForename());
        signer.setSurname(signerForm.getSurname());
        signer.setEmail(signerForm.getEmail());
        signer.setPetition(petition);
        // add the signer to the petition
        petition.getSigners().add(signer);
        // save the petition in the DB (Update it)
        petitionRepository.save(petition);
    }


    public List<Petition> searchPetitions(String keyword) {
        System.out.println("Search query: " + keyword);
        // search title & description (case-insensitive) for the keyword
        return petitionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }


}


