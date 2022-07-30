package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.wrapper.UniversalResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.UniqueConstraint;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired CrudOperation crudOperation;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UniversalResponseWrapper createNewCustomer(Customer customer) {

        try {
            // create customer
            Customer newCustomer = crudOperation.createCustomer(customer);
            //create account
            Account customerAccount = new Account();
            customerAccount.setCustomer(newCustomer);
            customerAccount.setMobileNumber(newCustomer.getPhoneNumber());

            Account createdAccount = crudOperation.createAccount(customerAccount);

            return new UniversalResponseWrapper(200, "customer created successfully",createdAccount);
        }catch (Exception e){
            return new UniversalResponseWrapper(500, "ERROR Creating customer");
        }
    }

    @Override
    public UniversalResponseWrapper getCustomerData(String mobileNumber) {

        Account account = crudOperation.fetchAccountNumber(mobileNumber);

        return account != null ? new UniversalResponseWrapper(200, "customer information retrieved successfully",
                account) : new UniversalResponseWrapper(500, "customer not found");
    }

    @Override
    public UniversalResponseWrapper applyLoan(String mobileNumber) {
        return null;
    }
}
