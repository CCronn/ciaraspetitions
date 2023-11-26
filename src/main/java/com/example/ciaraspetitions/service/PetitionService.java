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
        Petition createdPetition = petitionRepository.save(petition);
        return createdPetition;
    }


    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }


    @Transactional
    public Petition getPetitionEntityById(Long id) {
        Petition petition = petitionRepository.findById(id)
                .orElseThrow(() -> new PetitionNotFoundException("Petition not found with id: " + id));

        return petition;
    }


    @Transactional
    public void signPetition(Long petitionId, Signer signerForm) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new PetitionNotFoundException("Petition not found with id: " + petitionId));

        Signer signer = new Signer();
        signer.setForename(signerForm.getForename());
        signer.setSurname(signerForm.getSurname());
        signer.setEmail(signerForm.getEmail());
        signer.setPetition(petition);

        petition.getSigners().add(signer);
        petitionRepository.save(petition);
    }


    public List<Petition> searchPetitions(String keyword) {
        System.out.println("Search query: " + keyword);
        return petitionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }


}


