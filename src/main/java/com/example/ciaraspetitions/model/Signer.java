package com.example.ciaraspetitions.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Signer {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "signer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "petition_id")
    @JsonBackReference
    private Petition petition;

    private String forename;
    private String surname;
    private String email;

    // default constructor
    public Signer() {
    }

    // constructor
    public Signer(String forename, String surname, String email, Petition petition) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.petition = petition;
    }

    @Override
    public String toString() {
        // needed for search or throws error
        return "Signer{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
