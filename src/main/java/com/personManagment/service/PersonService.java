package com.personManagment.service;

import com.personManagment.domain.dto.PersonDTO;
import com.personManagment.domain.entity.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    PersonDTO createPerson(PersonDTO personDTO);

    List<PersonDTO> findAllPeople();

    List<PersonDTO> findAllPeopleByCriteria(String search);

    PersonDTO getPersonById(UUID id);

    String deletePersonne(UUID id);

    PersonDTO updatePerson(PersonDTO personDTO);
}
