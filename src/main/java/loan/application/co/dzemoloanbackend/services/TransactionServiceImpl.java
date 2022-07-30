package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.Transaction;
import loan.application.co.dzemoloanbackend.wrapper.FetchWrapper;
import loan.application.co.dzemoloanbackend.wrapper.RequestWrapper;
import loan.application.co.dzemoloanbackend.wrapper.UniversalResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired private CrudOperation crudOperation;
    @Override
    public UniversalResponseWrapper fundsTransfer(RequestWrapper requestWrapper) {

        Account fromAccount = crudOperation.fetchAccountByAccountNumber(requestWrapper.getAccountFrom());
        if (fromAccount == null) return new UniversalResponseWrapper(500, "debit acocunt not found");

        Account toAccount = crudOperation.fetchAccountByAccountNumber(requestWrapper.getAccountTo());
        if (toAccount == null) return new UniversalResponseWrapper(500, "credit acocunt not found");



        // validate the transaction data
        return ProcessTransaction(requestWrapper, fromAccount, toAccount);
    }

    @Override
    public UniversalResponseWrapper loanApplication(RequestWrapper requestWrapper) {

        // FOR LOAN APPLICATION - DEBIT CUSTOMER , CREDIT GL COLLECTION ACCOUNT
        Account fromAccount = crudOperation.fetchAccountByAccountNumber(requestWrapper.getAccountFrom());
        if (fromAccount == null) return new UniversalResponseWrapper(500, "debit acocunt not found");

        // the to account will be the collection account...

        Account toAccount = crudOperation.fetchAccountByAccountNumber("");
        if (toAccount == null) return new UniversalResponseWrapper(500, "credit acocunt not found");

        return ProcessTransaction(requestWrapper, fromAccount, toAccount);
    }

    @Override
    public UniversalResponseWrapper loanRepayment(RequestWrapper requestWrapper) {
        // FOR LOAN APPLICATION - CREDIT CUSTOMER , DEBIT GL COLLECTION ACCOUNT

        Account fromAccount =crudOperation.fetchAccountByAccountNumber("");
        if (fromAccount == null) return new UniversalResponseWrapper(500, "debit acocunt not found");

        // the to account will be the collection account...

        Account toAccount =  crudOperation.fetchAccountByAccountNumber(requestWrapper.getAccountFrom());
        if (toAccount == null) return new UniversalResponseWrapper(500, "credit acocunt not found");

        return ProcessTransaction(requestWrapper, fromAccount, toAccount);
    }

    @Override
    public UniversalResponseWrapper fetchTransactionHistory(FetchWrapper fetchWrapper) {

        Pageable pageable = PageRequest.of(fetchWrapper.getPage(), fetchWrapper.getSize(), Sort.by(Sort.Direction.ASC,"id"));

        return new UniversalResponseWrapper(200, "data retrieved successfully",
                crudOperation.fetchAllTransactions(fetchWrapper.getFrom().atStartOfDay()
                        ,fetchWrapper.getTo().atStartOfDay(),fetchWrapper.getAccountNumber(),pageable));
    }

    private UniversalResponseWrapper ProcessTransaction(RequestWrapper requestWrapper, Account fromAccount, Account toAccount) {
        // validate the transaction data
        new ValidateTransaction(fromAccount, toAccount,
                requestWrapper.getAmount());

        // perform accounting entries

        String transactionReference = String.valueOf(System.currentTimeMillis());

        //debit account
        Account debitAccount = new CustomerBalance(requestWrapper.getAmount(), true, fromAccount).updateBalances();
        Transaction debitTransaction = new GenerateTransaction(debitAccount, true,transactionReference ).generateTransactionData();

        //credit account
        Account creditAccount = new CustomerBalance(requestWrapper.getAmount(), true, toAccount).updateBalances();
        Transaction creditTransaction = new GenerateTransaction(creditAccount, true,transactionReference ).generateTransactionData();

        // update the transactions in the database
        Account[] accounts = new Account[]{debitAccount, creditAccount};
        Transaction[] transactions = new Transaction[]{debitTransaction, creditTransaction};

        crudOperation.updateCustomerBalances(Arrays.asList(accounts));
        crudOperation.saveTransactions(Arrays.asList(transactions));

        return new UniversalResponseWrapper(200, "funds transfer was successful",
                "REF:" + transactionReference);
    }
}
