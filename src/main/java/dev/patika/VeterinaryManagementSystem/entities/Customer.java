package dev.patika.VeterinaryManagementSystem.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * This class represents the Customer entity for the Veterinary Management System.
 * It includes details about the customer such as their ID, name, phone number, email, address, and city.
 * It also defines the relationship between the Customer entity and the Animal entity.
 * It uses Jakarta Persistence API (JPA) annotations for ORM mapping to the "customers" table in the database.
 * Lombok annotations are used for generating boilerplate code like getters, setters, constructors, etc.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "customer_mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "customer_address", length = 255)
    private String address;

    @Column(name = "customer_city", length = 255)
    private String city;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Animal> animalList;


}
