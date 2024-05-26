package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.core.config.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utiles.Msg;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorManager implements IDoctorService {
    // constructor to inject the DoctorRepo dependency
    private final DoctorRepo doctorRepo;

    // Constructor injection
    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    // To register a doctor
    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor); // DoctorRepo'nun save metodu kullanılır
    }


    // To fetch the doctor by ID
    @Override
    public Doctor get(Long id) {
        // Use findById in DoctorRepo, if not found, throw NotFound exception
        return this.doctorRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    // To bring doctors as paginated
    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        // PageRequest is used for paging
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepo.findAll(pageable);// DoctorRepo's findAll method is used
    }

    // To update the Doctor
    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);// DoctorRepo's save method is used
    }

    // To delete the Doctor
    @Override
    public boolean delete(long id) {
        Doctor doctor=this.get(id);
        this.doctorRepo.delete(doctor);// deleted with DoctorRepo's delete method
        return true;// Return true because the deletion was successful
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }

    @Override
    public boolean isDoctorAvailable(Long id, LocalDate appointmentDate) {
        return false;
    }


    @Override
    public boolean isAvailableOnDate(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doktor bulunamadı."));

        // Considering a method that includes the days the doctor is available, the following control can be achieved
        return doctor.isAvailableOnDate(date);
    }



}
