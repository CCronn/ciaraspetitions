package com.example.ciaraspetitions.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Petition {
    // Attributes
    @Id
    @GeneratedValue
    @Column(name = "petition_id")
    private Long petition_id;

    private String title;
    private String description;
    private String forename;
    private String surname;
    private String email;
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "petition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Signer> signers;

    // default constructor
    public Petition() {
    }
    // constructor
    public Petition(Long petition_id, String title, String description, String forename, String surname,
                    String email, LocalDateTime timestamp, List<Signer> signers) {
        this.petition_id = petition_id;
        this.title = title;
        this.description = description;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.timestamp = timestamp;
        this.signers = signers;
    }
}
