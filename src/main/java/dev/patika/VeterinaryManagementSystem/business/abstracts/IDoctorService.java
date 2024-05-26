package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.data.domain.Page;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;

public interface IDoctorService {
    // Saves a new doctor
    Doctor save(Doctor doctor);
    // Retrieves a doctor by their ID
    Doctor get(Long id);
    // Retrieves a paginated list of doctors based on the page number and page size
    Page<Doctor> cursor(int page, int pageSize);
    // Updates the information of an existing doctor
    Doctor update (Doctor doctor);
    // Deletes a doctor by their ID
    boolean delete (long id);
    // Retrieves a list of all doctors
    List<Doctor> findAll();
    // Checks if a doctor with the specified ID is available on a given appointment date
    boolean isDoctorAvailable(Long id, LocalDate appointmentDate);
    // Checks if a doctor is available on a given date
    boolean isAvailableOnDate(Long doctorId, LocalDate date);
}
