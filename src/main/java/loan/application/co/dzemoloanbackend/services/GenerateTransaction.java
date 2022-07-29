package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.Transaction;
import loan.application.co.dzemoloanbackend.wrapper.RequestWrapper;
import lombok.Data;

@Data
public class GenerateTransaction {

    RequestWrapper requestWrapper;
    Account account;
    private boolean debit;
    private String transactionReference;

    public GenerateTransaction(Account account, boolean debit,String transactionReference) {
        this.requestWrapper = requestWrapper;
        this.debit = debit;
        this.transactionReference = transactionReference;
    }

    Transaction generateTransactionData(){
        Transaction transaction = new Transaction();
        transaction.setNarration(this.requestWrapper.getNarration());
        transaction.setAccountNumber(this.account.getAccountNumber());
        transaction.setDebitCredit(this.debit ? 'D' : 'C');
        transaction.setAmount(this.requestWrapper.getAmount());
        transaction.setExternalReference(this.requestWrapper.getExternalReference());
        transaction.setBalanceBefore(this.account.getActualBalance());
        transaction.setTransactionReference(this.transactionReference);
        transaction.setCustomerNumber(this.account.getMobileNumber());
        transaction.setCustomerNames(this.getAccount().getCustomer().getFirstName()+ " "+this.getAccount().getCustomer().getLastName());

        return transaction;
    }
}
