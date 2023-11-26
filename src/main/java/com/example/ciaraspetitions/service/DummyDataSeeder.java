package com.example.ciaraspetitions.service;

import com.example.ciaraspetitions.model.*;
import com.example.ciaraspetitions.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class DummyDataSeeder {
    private final PetitionRepository petitionRepository;

    @Autowired
    public DummyDataSeeder(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
        seedPetitionData();
    }

    private void createAndSavePetition(String title, String description, String forename, String surname, String email) {
        Petition petition = new Petition();
        petition.setTitle(title);
        petition.setDescription(description);
        petition.setForename(forename);
        petition.setSurname(surname);
        petition.setEmail(email);
        petition.setTimestamp(LocalDateTime.now());

        List<Signer> signers = createDummySigners(petition);
        petition.setSigners(signers);

        petitionRepository.save(petition);
    }

    private void seedPetitionData() {
        // Add dummy data to the repository
        createAndSavePetition("Petition 1", "Description 1", "User 1 Forename", "User 1 Surname", "user1@example.com");
        createAndSavePetition("Petition 2", "Description 2", "User 2 Forename", "User 2 Surname", "user2@example.com");
    }


    private List<Signer> createDummySigners(Petition petition) {
        List<Signer> signers = new ArrayList<>();
        signers.add(new Signer("Signer 1 Forename", "Signer 1 Surname", "signer1@example.com", petition));
        signers.add(new Signer("Signer 2 Forename", "Signer 2 Surname", "signer2@example.com", petition));
        return signers;
    }
}
