package com.personManagment.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "people")
@Setter
@Getter
public class Person {
    /**
     * id
     */
    @Id
    private UUID id;

    /**
     * nom
     */
    private String nom;

    /**
     * prenom
     */
    private String prenom;
    public Person(){
        this.id = UUID.randomUUID();
    }

    public Person(String prenom, String nom) {
        this.id = UUID.randomUUID();
        this.prenom = prenom;
        this.nom = nom;
    }
}
