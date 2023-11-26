package com.example.ciaraspetitions.service;

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


}


