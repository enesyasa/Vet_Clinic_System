package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utiles.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.Request.AppointmentSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentResponse;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService,
                                 IAnimalService animalService,
                                 IDoctorService doctorService,
                                 IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.animalService = animalService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    // Creates a new appointment
    @PostMapping("/createdNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest ){
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest,Appointment.class);
        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment,AppointmentResponse.class));
    }

    // Fetches a specific appointment
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get (@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
    }



    // Filters appointments by doctor ID and date range
    @GetMapping("/filter/doctor/{doctorId}")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorIdAndDateRange(
            @PathVariable("doctorId") long doctorId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Appointment> appointments = appointmentService.getByDoctorIdAndDateTimeBetween(
                doctorId,
                startDate,
                endDate
        );

        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(appointmentResponses);
    }


    // Filters appointments by animal ID and date range
    @GetMapping("/filter/animal/{animalId}")
    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalIdAndDateRange(
            @PathVariable("animalId") long animalId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Appointment> appointments = appointmentService.getByAnimalIdAndDateTimeBetween(animalId, startDate, endDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }


    // Fetches appointments based on a specific date range
    @GetMapping("/filter/date")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        // retrieves appointments between startDate and endDate parameters
        List<Appointment> appointments = appointmentService.getAppointmentsByDateRange(startDate, endDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    //Deletes a specific Appointment.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.Ok();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findAll() {
        List<Appointment> appointments = appointmentService.findAll();

        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(appointmentResponses);
    }

}
