package com.personManagment.repository;

import com.personManagment.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    List<Person> findByNomIgnoreCaseContainingOrPrenomIgnoreCaseContaining(String nom,String prenom);
}
