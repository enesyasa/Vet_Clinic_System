package dev.patika.VeterinaryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * This class represents the Appointment entity for the Veterinary Management System.
 * It includes details about the appointment such as its ID, date and time,
 * and its relationships with the Doctor and Animal entities.
 * It uses Jakarta Persistence API (JPA) annotations for ORM mapping to the "appointments" table in the database.
 * Lombok annotations are used for generating boilerplate code like getters, setters, constructors, etc.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDateTime;


    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne()
    @JoinColumn(name ="appointment_animal_id",referencedColumnName = "animal_id")
    private Animal animal;


    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDateTime=" + appointmentDateTime  +
                '}';
    }
}
