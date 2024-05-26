package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/*
 * This class represents the Vaccine entity for the Veterinary Management System.
 * It includes details about the vaccine such as its ID, name, code, protection start date, and protection finish date.
 * It also defines the relationship between the Vaccine entity and the Animal entity.
 * It uses Jakarta Persistence API (JPA) annotations for ORM mapping to the "vaccines" table in the database.
 * Lombok annotations are used for generating boilerplate code like getters, setters, constructors, etc.
 */

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name = "vaccine_name", nullable = false)
    private String name;

    @Column(name = "vaccine_code",nullable = false)
    private String code;

    @Column(name = "vaccine_protection_start_date")
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_protection_finish_date")
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", protectionStartDate=" + protectionStartDate +
                ", protectionFinishDate=" + protectionFinishDate +
                '}';
    }

}
