package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

// The IAppointmentService interface defines various operations related to appointments
public interface IAppointmentService {
    // Saves a new appointment
    Appointment save(Appointment appointment);

    // Retrieves an appointment by its ID
    Appointment get(Long id);

    // Deletes an appointment by its ID
    boolean delete (long id);

    // Retrieves a list of appointments for a specific doctor within a given date and time range
    List<Appointment> getByDoctorIdAndDateTimeBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    // Retrieves a list of appointments for a specific animal within a given date and time range
    List<Appointment> getByAnimalIdAndDateTimeBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate);

    // Retrieves a list of appointments within a given date range (as strings)
    List<Appointment> getAppointmentsByDateRange(String startDate, String endDate);

    // Checks if an appointment exists at a specific date and time
    boolean existsByAppointmentDateTime(LocalDateTime appointmentDateTime);

    // Retrieves a list of all appointments
    List<Appointment> findAll();
}
