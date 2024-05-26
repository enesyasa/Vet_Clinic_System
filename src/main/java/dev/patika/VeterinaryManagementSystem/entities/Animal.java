package dev.patika.VeterinaryManagementSystem.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

/*
 * This class represents the Animal entity for the Veterinary Management System.
 * It includes details about the animal such as its ID, name, species, breed, color,
 * date of birth, gender, and relationships with Customer, Vaccine, and Appointment entities.
 * It uses Jakarta Persistence API (JPA) annotations for ORM mapping to the "animals" table in the database.
 * Lombok annotations are used for generating boilerplate code like getters, setters, constructors, etc.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_name", nullable = false)
    private String name;

    @Column(name = "animal_species", nullable = false)
    private String species;

    @Column(name = "breed", length = 255, nullable = false)
    private String breed;

    @Column(name = "animal_colour", length = 255, nullable = false)
    private String colour;

    //@Temporal(TemporalType.DATE)
    @Column(name = "animal_date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "animal_gender")
    private Gender gender;
    public enum Gender {
        MALE,
        FEMALE
    }

    @ManyToOne
    @JoinColumn(name = "customer_id") // Customer entity'sindeki id ile ilişkilendirir
    private Customer customer;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Appointment> appointment;

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", colour='" + colour + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }


}
