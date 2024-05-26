package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {


    // Saves a new vaccine for a specific animal
    Vaccine save(Vaccine vaccine, Long animalId);

    // Retrieves a vaccine by its ID
    Vaccine get(Long id);

    // Retrieves a paginated list of vaccines based on the page number and page size
    Page<Vaccine> cursor(int page, int pageSize);

    // Updates the information of an existing vaccine
    Vaccine update (Vaccine vaccine);

    // Deletes a vaccine by its ID
    boolean delete (long id);

    // Retrieves a list of vaccines by the animal's ID
    List<Vaccine> getVaccinesByAnimalId(Long animalId);

    // To retrieve vaccinations from a specific date range
    List<Vaccine> getVaccinesByDateRangeForAnimal(Long animalId, LocalDate startDate, LocalDate endDate);

    //Inquiry by vaccination date and Animal id
    List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);

    // Retrieves a list of vaccines within a specific date range
    List<Vaccine> getVaccinesByDateRange(LocalDate startDate, LocalDate endDate);

    // Retrieves a list of vaccines within a specific protection start date range
    List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Retrieves a list of all vaccines
    List<Vaccine> findAll();
}
