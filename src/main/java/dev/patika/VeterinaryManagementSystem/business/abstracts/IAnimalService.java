package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import org.springframework.data.domain.Page;

import java.util.List;

// The IAnimalService interface defines various operations related to animals
public interface IAnimalService {
    // Saves a new animal for a specific customer
    Animal save(Animal animal,Long customerId);

    // Retrieves an animal by its ID
    Animal get(Long id);

    // Retrieves a paginated list of animals based on the page number and page size
    Page<Animal> cursor(int page, int pageSize);

    // Updates the information of an existing animal
    Animal update (Animal animal);

    // Deletes an animal by its ID
    boolean delete (long id);

    // Retrieves a list of animals by their name
    List<Animal> getAnimalsByName(String name);

    // Retrieves a list of animals by the customer's ID
    List<Animal> getAnimalsByCustomerId(Long customerId);

    // Retrieves a list of all animals
    List<Animal> findAll();
}


