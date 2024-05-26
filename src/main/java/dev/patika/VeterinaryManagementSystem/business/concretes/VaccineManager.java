package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.VeterinaryManagementSystem.core.config.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utiles.Msg;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepo;
import dev.patika.VeterinaryManagementSystem.repository.VaccineRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class VaccineManager implements IVaccineService {

    // constructor to inject the VaccineRepo dependency
    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;

    // Constructor injection
    public VaccineManager(VaccineRepo vaccineRepo, AnimalRepo animalRepo) {
        this.vaccineRepo = vaccineRepo;
        this.animalRepo = animalRepo;
    }

    // To register a new vaccine
    @Override
    public Vaccine save(Vaccine vaccine, Long animalId) {
        Animal animal = animalRepo.findById(animalId)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        // Check Protectorate end date
        LocalDate currentDate = LocalDate.now();
        LocalDate protectionFinishDate = vaccine.getProtectionFinishDate();
        if (protectionFinishDate != null && protectionFinishDate.isBefore(currentDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Koruyuculuk bitiş tarihi geçmiş bir aşı eklenemez.");
        }
        vaccine.setAnimal(animal);
        return vaccineRepo.save(vaccine);
    }

    // To fetch vaccine by ID
    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    // To bring vaccines as paginated
    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    // To update the vaccine
    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    // To delete the vaccine
    @Override
    public boolean delete(long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    // Hayvan ID'sine göre aşıları getirmek için
    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {

        return vaccineRepo.findByAnimalId(animalId);
    }
    // Belirli bir tarih aralığındaki hayvanın aşı kayıtlarını getirmek için
    @Override
    public List<Vaccine> getVaccinesByDateRangeForAnimal(Long animalId, LocalDate startDate, LocalDate endDate) {
        // Belirli bir tarih aralığındaki hayvanın aşı kayıtlarını getir
        return vaccineRepo.findByAnimalIdAndProtectionStartDateBetween(animalId, startDate, endDate);
    }

    @Override
    public List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(animalId, Msg.NOT_FOUND);
        Objects.requireNonNull(startDate, Msg.NOT_FOUND);
        Objects.requireNonNull(endDate, Msg.NOT_FOUND);

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(Msg.HISTORY_CONTROLLER);
        }
        return vaccineRepo.findByAnimalIdAndProtectionStartDateBetween(animalId, startDate, endDate);

    }

    // Belirli bir tarih aralığındaki tüm aşı kayıtlarını getirmek için
    @Override
    public List<Vaccine> getVaccinesByDateRange(LocalDate startDate, LocalDate endDate) {
        return vaccineRepo.findByProtectionStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate,Msg.NOT_FOUND);
        Objects.requireNonNull(endDate, Msg.NOT_FOUND);

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(Msg.HISTORY_CONTROLLER);
        }
        return vaccineRepo.findByProtectionStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepo.findAll();
    }

}



