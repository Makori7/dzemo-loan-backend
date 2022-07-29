package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.AccountClass;
import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.entity.Transaction;
import loan.application.co.dzemoloanbackend.repository.AccountClassRepository;
import loan.application.co.dzemoloanbackend.repository.AccountRepository;
import loan.application.co.dzemoloanbackend.repository.CustomerRepository;
import loan.application.co.dzemoloanbackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudOperationImpl implements CrudOperation {

    @Autowired private AccountRepository accountRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private AccountClassRepository accountClassRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<AccountClass> createAccountClass(List<AccountClass> accountClasses) {
        return (List<AccountClass>) accountClassRepository.saveAll(accountClasses);
    }

    @Override
    public Account fetchAccountNumber(String mobileNumber) {
        return accountRepository.findFirstByMobileNumber(mobileNumber).orElse(null);
    }

    @Override
    public Account fetchAccountByAccountNumber(String accountNumber) {
        return accountRepository.findFirstByAccountNumber(accountNumber).orElse(null);
    }

    @Override
    public List<Transaction> saveTransactions(List<Transaction> transactions) {
        return (List<Transaction>) transactionRepository.saveAll(transactions);
    }

    @Override
    public List<Account> updateCustomerBalances(List<Account> accounts) {
        return (List<Account>) accountRepository.saveAll(accounts);
    }
}
