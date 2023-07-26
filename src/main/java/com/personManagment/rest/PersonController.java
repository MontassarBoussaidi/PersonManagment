package com.personManagment.rest;

import com.personManagment.domain.dto.PersonDTO;
import com.personManagment.exception.BadRequestException;
import com.personManagment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping("/searchAll")
    public List<PersonDTO> getAllPeopleByCriteria(@RequestParam(required = false) String search) {
        if (search != null) {
            return personService.findAllPeopleByCriteria(search);
        } else {
            return personService.findAllPeople();
        }
    }

    @PostMapping("/addPerson")
    public PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
        if (Objects.isNull(personDTO.getNom()) ||personDTO.getNom().isBlank() || Objects.isNull(personDTO.getPrenom()) ||personDTO.getPrenom().isBlank()) {
            throw new BadRequestException("ERROR_MISSING_REQUIRED_DATA","FirstName and LastName can't be null !");
        }
        return personService.createPerson(personDTO);
    }


    @PutMapping("/updatePerson")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO) {
        if (Objects.isNull(personDTO.getId())) {
            throw new BadRequestException("ERROR_MISSING_REQUIRED_DATA","Id can't be null !");
        }
        return personService.updatePerson(personDTO);
    }
    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable("id") UUID id) {
            return personService.getPersonById(id);
        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePersonne(@PathVariable("id") UUID id) {
        String message = personService.deletePersonne(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    }




