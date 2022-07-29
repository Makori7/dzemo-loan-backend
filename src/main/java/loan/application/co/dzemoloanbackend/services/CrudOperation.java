package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.AccountClass;
import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.entity.Transaction;

import java.util.List;

public interface CrudOperation {

    Customer createCustomer(Customer customer);
    Account createAccount(Account account);

    List<AccountClass> createAccountClass(List<AccountClass> accountClasses);

    Account fetchAccountNumber(String mobileNumber);
    Account fetchAccountByAccountNumber(String accountNumber);

    List<Transaction> saveTransactions(List<Transaction> transactions);
    List<Account> updateCustomerBalances(List<Account> accounts);

}
