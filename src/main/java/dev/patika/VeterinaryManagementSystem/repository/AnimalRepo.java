package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * This interface represents the repository for the Animal entity in the Veterinary Management System.
 * It extends JpaRepository to provide CRUD operations and custom query methods for Animal entities.
 * The repository is annotated with @Repository to indicate it's a Spring Data repository.
 */

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {

    // This method returns a case-insensitive list of named animals.
    List<Animal> findByNameIgnoreCaseContaining(String name);

    // This method returns a list of animals with the given customer ID.
    List<Animal> findByCustomerId(Long customerId);
}
