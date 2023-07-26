package com.personManagment.service;

import com.personManagment.domain.dto.PersonDTO;
import com.personManagment.domain.entity.Person;
import com.personManagment.repository.PersonRepository;
import com.personManagment.service.impl.PersonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("Test Person service")
class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    private PersonDTO personDTO;
    private Person person;

    @BeforeEach
    public void setUp() {
        personDTO =new PersonDTO();
        personDTO.setNom("Martin");
        personDTO.setPrenom("Paul");

        person =new Person();
        person.setNom("Martin");
        person.setPrenom("Paul");

    }


    @Test
    void createPerson() {
        // given
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        // when
        PersonDTO result=personService.createPerson(personDTO);

        // then
        Mockito.verify(personRepository, Mockito.times(1)).save(Mockito.any(Person.class));
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getNom()).isEqualTo(person.getNom());

    }

    @Test
    void updatePerson() {
        personDTO.setId(person.getId());
        // given
        Mockito.when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        // when
        PersonDTO result=personService.updatePerson(personDTO);

        // then
        Mockito.verify(personRepository, Mockito.times(1)).save(Mockito.any(Person.class));
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getNom()).isEqualTo(person.getNom());

    }



    @Test
    void DeletePerson() {
        personDTO.setId(person.getId());
        // given
        Mockito.when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(person));

      //  Mockito.doNothing().when(personRepository.deleteById(personDTO.getId())).

        Mockito.doNothing().when(personRepository).deleteById(personDTO.getId());
        // when
        personService.deletePersonne(personDTO.getId());

        // then
        Mockito.verify(personRepository, Mockito.times(1)).findById(personDTO.getId());
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(personDTO.getId());


    }


}