package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utiles.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.Request.CustomerSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.Request.CustomerUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;
    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }
    // POST endpoint to register a new customer
    @PostMapping("/createdNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest ){
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest,Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer,CustomerResponse.class));
    }

    // GET endpoint to get customer details
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get (@PathVariable("id") long id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer,CustomerResponse.class));
    }

    // PUT endpoint to update customer information
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest ){
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest,Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer,CustomerResponse.class));
    }
    // DELETE endpoint to delete the customer
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.customerService.delete(id);
        return ResultHelper.Ok();
    }
    // GET endpoint to list customers paginally
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "2") int pageSize
    ){
        // Calling the relevant service method for paging
        Page<Customer> categoryPage = this.customerService.cursor(page,pageSize);
        Page<CustomerResponse> customerResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category,CustomerResponse.class));
        // Returning the result
        return  ResultHelper.cursor(customerResponsePage);
    }
    //an endpoint that filters customers by name
    @GetMapping("/filter") //http://localhost:8047/v1/customers/filter?name=Enes example search URL
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getCustomersByName(@RequestParam("name") String name) {
        List<Customer> customers = this.customerService.getCustomersByName(name);

        // We convert customers filtered by this name to CustomerResponse format
        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(customerResponses);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> findAll() {
        List<Customer> customers = customerService.findAll();

        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(customerResponses);
    }


}
