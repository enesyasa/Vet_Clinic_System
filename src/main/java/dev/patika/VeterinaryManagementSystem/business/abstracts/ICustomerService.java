package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

// The ICustomerService interface defines various operations related to customers
public interface ICustomerService {

    // Saves a new customer
    Customer save(Customer customer);

    // Retrieves a customer by their ID
    Customer get(long id);

    // Retrieves a paginated list of customers based on the page number and page size
    Page<Customer> cursor(int page, int pageSize);

    // Updates the information of an existing customer
    Customer update (Customer customer);

    // Deletes a customer by their ID
    boolean delete (long id);

    // Retrieves a list of customers by their name
    List<Customer> getCustomersByName(String name);

    // Retrieves a list of all customers
    List<Customer> findAll();

}
