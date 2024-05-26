package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.core.config.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utiles.Msg;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepo;
import dev.patika.VeterinaryManagementSystem.repository.CustomerRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {
    // constructor to inject the AnimalRepo dependency
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;

    public AnimalManager(AnimalRepo animalRepo, CustomerRepo customerRepo) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
    }

    // To register a new animal

    @Override
    public Animal save(Animal animal, Long customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Müşteri bulunamadı."));

        animal.setCustomer(customer);
        return animalRepo.save(animal);
    }

    // To fetch the animal by ID
    @Override
    public Animal get(Long id) {
        // Use findById in AnimalRepo, if not found, throw NotFound exception
        return this.animalRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    // To fetch animals in paginated form
    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        // PageRequest is used for paging
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepo.findAll(pageable); // AnimalRepo's findAll method is used
    }

    // To update the animal
    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepo.save(animal); // AnimalRepo's save method is used
    }


    // To delete the animal
    @Override
    public boolean delete(long id) {  // Fetch animal according to ID
        Animal animal =this.get(id);  // Deleted with AnimalRepo's delete method
        this.animalRepo.delete(animal); // Return true because the deletion was successful
        return true;
    }

    @Override
    public List<Animal> getAnimalsByName(String name) {
        // Returns animals whose names contain the specified animals without case sensitivity.
        return this.animalRepo.findByNameIgnoreCaseContaining(name);
    }
    @Override
    public List<Animal> getAnimalsByCustomerId(Long customerId) {
        // Fetches animals with the specified customer ID.
        return this.animalRepo.findByCustomerId(customerId);
    }

    @Override
    public List<Animal> findAll() {
        return this.animalRepo.findAll();
    }

}
