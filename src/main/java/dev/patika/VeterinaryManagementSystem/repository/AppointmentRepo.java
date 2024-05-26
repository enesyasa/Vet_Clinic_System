package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    boolean existsByAppointmentDateTime(LocalDateTime appointmentDateTime);

    // Find appointments by doctor ID and date range
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Find appointments by animal ID and date range
    List<Appointment> findByAnimalIdAndAppointmentDateTimeBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime);


}
