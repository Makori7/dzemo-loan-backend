package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.AccountClass;
import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudOperation {

    Customer createCustomer(Customer customer);
    Account createAccount(Account account);

    List<AccountClass> createAccountClass(List<AccountClass> accountClasses);

    Account fetchAccountNumber(String mobileNumber);
    Account fetchAccountByAccountNumber(String accountNumber);

    List<Transaction> saveTransactions(List<Transaction> transactions);
    List<Account> updateCustomerBalances(List<Account> accounts);

    Page<Transaction> fetchAllTransactions(LocalDateTime from, LocalDateTime to, String accountNumber, Pageable pageable);

}
