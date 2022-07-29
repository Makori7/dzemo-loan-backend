package loan.application.co.dzemoloanbackend.controller;

import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.services.CustomerService;
import loan.application.co.dzemoloanbackend.wrapper.UniversalResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired private CustomerService customerService;

    @PostMapping("/create-customer")
    public UniversalResponseWrapper createCustomer(@RequestBody Customer customer){
        return customerService.createNewCustomer(customer);
    }
    @GetMapping("/get-customer-details/{phoneNumber}")
    public UniversalResponseWrapper createCustomer(@PathVariable String phoneNumber){

        return customerService.getCustomerData(phoneNumber);
    }
}
