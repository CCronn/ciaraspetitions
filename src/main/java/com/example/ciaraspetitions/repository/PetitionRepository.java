package com.example.ciaraspetitions.repository;

import com.example.ciaraspetitions.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PetitionRepository extends JpaRepository<Petition, Long> {

}

