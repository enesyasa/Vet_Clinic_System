package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/*
 * This class represents the AvailableDate entity for the Veterinary Management System.
 * It includes details about the available date such as its ID, the date,
 * and its relationship with the Doctor entity.
 * It uses Jakarta Persistence API (JPA) annotations for ORM mapping to the "availabledates" table in the database.
 * Lombok annotations are used for generating boilerplate code like getters, setters, constructors, etc.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "availabledates")
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availableDate_id")
    private Long id;

    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availableDate_doctor_id")
    private Doctor doctor;

}
