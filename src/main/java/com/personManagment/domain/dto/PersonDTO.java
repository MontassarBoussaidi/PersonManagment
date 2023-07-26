package com.personManagment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonDTO {

    private UUID id;

    /**
     * nom
     */
    private String nom;

    /**
     * prenom
     */
    private String prenom;
}
