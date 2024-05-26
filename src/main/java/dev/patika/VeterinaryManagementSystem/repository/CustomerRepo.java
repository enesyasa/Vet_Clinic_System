package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    // This method returns a case-insensitive list of customers containing the given name.
    List<Customer> findByNameContainingIgnoreCase(String name);
}
