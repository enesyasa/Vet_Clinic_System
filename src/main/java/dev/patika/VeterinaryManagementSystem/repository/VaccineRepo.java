package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine,Long> {

    // This method returns a list of vaccines with the given animal ID.
    List<Vaccine> findByAnimalId(Long animalId);

    // This method returns a list of vaccines with a given animal ID and a given range of protection start dates.
    List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);

    // This method returns a list of vaccines within a given range of protection start dates.
    List<Vaccine> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);


}
