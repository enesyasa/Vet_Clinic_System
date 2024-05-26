package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;

import java.util.List;

// The IAvailableDateService interface defines various operations related to available dates for doctors
public interface IAvailableDateService {

    // Saves a new available date for a specific doctor
    AvailableDate save(AvailableDate availableDate,Long doctorId);

    // Retrieves an available date by its ID
    AvailableDate get(Long id);

    // Deletes an available date by its ID
    boolean delete (long id);

    // Retrieves a list of all available dates for a specific doctor by the doctor's ID
    List<AvailableDate> getAllByDoctorId(Long id);

    // Retrieves a list of all available dates
    List<AvailableDate> findAll();

}
