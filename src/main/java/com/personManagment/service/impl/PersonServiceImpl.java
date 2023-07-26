package com.personManagment.service.impl;

import com.personManagment.domain.dto.PersonDTO;
import com.personManagment.domain.entity.Person;
import com.personManagment.exception.DataNotFoundException;
import com.personManagment.repository.PersonRepository;
import com.personManagment.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {

        Person person=new Person();
        person.setNom(personDTO.getNom());
        person.setPrenom(personDTO.getPrenom());
        personRepository.save(person);
        personDTO.setId(person.getId());

        return personDTO;
    }

    @Override
    public List<PersonDTO> findAllPeople() {

        List<Person> personList=personRepository.findAll();
        List<PersonDTO> personDTOS=new ArrayList<>();
        personList.forEach(p->{
           PersonDTO personDTO=modelMapper.map(p, PersonDTO.class);
            personDTOS.add(personDTO);
        });
        return personDTOS;

    }

    @Override
    public PersonDTO getPersonById(UUID id) {
        if(Objects.nonNull(id)){
            Optional<Person> person= personRepository.findById(id);
            if(Objects.nonNull(person)){
                PersonDTO personDTO=modelMapper.map(person.get(), PersonDTO.class);
                return personDTO;
            }else
                throw new DataNotFoundException("ERROR_NOT_FOUND_EXCEPTION","Person with specified id not found!");
        }

        return null;
    }

    @Override
    public String deletePersonne(UUID id) {
        if(Objects.nonNull(id)){
            Optional<Person> person= personRepository.findById(id);
            if(person.isPresent()){
                personRepository.deleteById(id);
                return "The person was successfully deleted";

            }else
                throw new DataNotFoundException("ERROR_NOT_FOUND_EXCEPTION","Person with specified id not found!");
        }
        return null;
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        Optional<Person> person= personRepository.findById(personDTO.getId());
        if(person.isPresent()){
            person.get().setNom(personDTO.getNom());
            person.get().setPrenom(personDTO.getPrenom());
            personRepository.save(person.get());

        }else
            throw new DataNotFoundException("ERROR_NOT_FOUND_EXCEPTION","Person with specified id not found!");

        return personDTO;
    }

    @Override
    public List<PersonDTO> findAllPeopleByCriteria(String search) {
        List<Person> personList=personRepository.findByNomIgnoreCaseContainingOrPrenomIgnoreCaseContaining(search,search);
        List<PersonDTO> personDTOS=new ArrayList<>();
        personList.forEach(p->{
            PersonDTO personDTO=modelMapper.map(p, PersonDTO.class);
            personDTOS.add(personDTO);
        });
        return personDTOS;
    }
}
