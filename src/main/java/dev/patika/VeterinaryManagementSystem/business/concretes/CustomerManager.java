package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utiles.Msg;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.repository.CustomerRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements ICustomerService {

    // constructor to inject the CustomerRepo dependency
    private final CustomerRepo customerRepo;

    // Constructor injection
    public CustomerManager(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    // To register a new customer
    @Override
    public Customer save(Customer customer) {
        // CustomerRepo's save method is used
        return this.customerRepo.save(customer);
    }

    // To fetch customer by ID
    @Override
    public Customer get(long id) {
        // Use findById in CustomerRepo, if not found, throw NotFound exception
        return customerRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    // To bring customers in paginated
    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        // PageRequest is used for paging
        Pageable pageable = PageRequest.of(page,pageSize);
        // CustomerRepo's findAll method is used
        return this.customerRepo.findAll(pageable);
    }

    // To update the customer
    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    // To delete a customer
    @Override
    public boolean delete(long id) {
        Customer customer = this.get(id);// Fetch customer according to ID
        this.customerRepo.delete(customer);// Deleted with CustomerRepo's delete method
        return true; // Return true because the deletion was successful
    }

    // To fetch customers with a name
    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepo.findByNameContainingIgnoreCase(name);// Retrieves customers with name
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepo.findAll();
    }
}
