package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findAllByDoctorId(Long doctorId);
    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate, Long doctorId);

}
