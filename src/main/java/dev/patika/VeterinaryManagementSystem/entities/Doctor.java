package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/*
 * This class represents the Doctor entity for the Veterinary Management System.
 * It includes details about the doctor such as their ID, name, phone number, email, address, and city.
 * It also defines the relationships between the Doctor entity and the AvailableDate and Appointment entities.
 * Additionally, it includes a method to check if the doctor is available on a specific date.
 * It uses Jakarta Persistence API (JPA) annotations for ORM mapping to the "doctors" table in the database.
 * Lombok annotations are used for generating boilerplate code like getters, setters, constructors, etc.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "doctor_name", nullable = false)
    private String name;

    @Column(name = "doctor_phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "doctor_mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "doctor_address", length = 255)
    private String address;

    @Column(name = "doctor_city", length = 255)
    private String city;



    // Doctor sınıfına availableDates alanını ekleyin
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDates;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;

    public boolean isAvailableOnDate(LocalDate date) {
        return availableDates != null && availableDates.stream()
                .anyMatch(availableDate -> availableDate.getAvailableDate().equals(date));
    }



}
