package dev.patika.VeterinaryManagementSystem.api;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utiles.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.Request.AnimalSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.Request.AnimalUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")

public class AnimalController {
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    // Constructor-based dependency injection
    public AnimalController(IAnimalService animalService,
                            ICustomerService customerService,
                            IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    // Endpoint retrieving details of a specific animal
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get (@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal,AnimalResponse.class));
    }

    // Add new animal endpoint
    @PostMapping("/createdNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        Animal animal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        Animal savedAnimal = this.animalService.save(animal, animalSaveRequest.getCustomerId());
        return ResultHelper.created(this.modelMapper.forResponse().map(savedAnimal, AnimalResponse.class));
    }

    // Animal update endpoint
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest ){
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest,Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal,AnimalResponse.class));
    }

    // Animal deletion endpoint
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.Ok();
    }
    // Endpoint fetching vaccines for the animal
    @GetMapping("/{id}/vaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesForAnimal(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);

        // Here, you can get all vaccination records for the animal as animal.getVaccines()
        List<Vaccine> vaccines = animal.getVaccines();

        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByName(@RequestParam("name") String name) {
        List<Animal> animals = this.animalService.getAnimalsByName(name);

        // Converting animals filtered by name to AnimalResponse format
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(animalResponses);
    }

    // to fetch animals by customer ID
    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("customerId") Long customerId) {
        List<Animal> animals = this.animalService.getAnimalsByCustomerId(customerId);

        // We convert the owner's animals to AnimalResponse format
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(animalResponses);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAll() {
        List<Animal> animals = this.animalService.findAll();
        List<AnimalResponse> animalResponses = new ArrayList<>();

        for (Animal animal : animals) {
            AnimalResponse response = new AnimalResponse();
            response.setName(animal.getName());
            response.setSpecies(animal.getSpecies());
            response.setBreed(animal.getBreed());
            response.setGender(String.valueOf(animal.getGender()));
            response.setColour(animal.getColour());
            response.setDateOfBirth(animal.getDateOfBirth());
            // animal.getCustomer().getId() ile müşteri ID'sini alıyoruz
            response.setCustomerId(animal.getCustomer().getId());

            animalResponses.add(response);
        }

        return ResultHelper.success(animalResponses);
    }
}
