package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.wrapper.UniversalResponseWrapper;

public interface CustomerService {

    UniversalResponseWrapper createNewCustomer(Customer customer);
    UniversalResponseWrapper getCustomerData(String mobileNumber);

    UniversalResponseWrapper applyLoan(String mobileNumber);
}
