package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAvailableDateService;
import dev.patika.VeterinaryManagementSystem.core.config.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utiles.Msg;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.repository.AvailableDateRepo;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepo;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, DoctorRepo doctorRepo) {
        this.availableDateRepo = availableDateRepo;
        this.doctorRepo = doctorRepo;
    }


    @Override
    public AvailableDate save(AvailableDate availableDate, Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doktor bulunamadı."));

        LocalDate dateToCheck = availableDate.getAvailableDate();
        // Check if availableDate for the same doctor exists for a given date
        Optional<AvailableDate> existingDate = availableDateRepo.findByAvailableDateAndDoctorId(dateToCheck, doctorId);
        if (existingDate.isPresent()) {
            // Throw an error if there is one or return the current one
            throw new RuntimeException("Bu tarih için zaten bir kayıt mevcut.");

        }

        availableDate.setDoctor(doctor);
        return availableDateRepo.save(availableDate);
    }


    public List<AvailableDate> getAllByDoctorId(Long doctorId) {
        return availableDateRepo.findAllByDoctorId(doctorId);
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepo.findAll();
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    //deletes a specific AvailableDate.
    @Override
    public boolean delete(long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

}
