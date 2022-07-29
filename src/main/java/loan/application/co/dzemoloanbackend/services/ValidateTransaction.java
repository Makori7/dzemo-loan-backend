package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.AccountClass;
import loan.application.co.dzemoloanbackend.exception.ValidationException;
import lombok.Data;

@Data
public  class ValidateTransaction {

    private Account fromAccount;
    private Account toAccount;
    private double amount;

    public ValidateTransaction(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;

        nullValues();
        blocked(this.fromAccount);
        blocked(this.toAccount);
        limitsCheck();
        validateBalance();
    }

    void blocked(Account account){
        if (account.isBlocked()) throw new ValidationException("Account blocked");
    }

    void limitsCheck(){
        AccountClass accountClass = fromAccount.getAccountClass();
        if (this.amount > accountClass.getAccountLimit())
            throw new ValidationException("Amount above transaction limit of "+accountClass.getCurrency()+" "+
                    accountClass.getTransactionLimit());
    }

    void validateBalance(){

        if (this.amount > this.getFromAccount().getAvailableBalance())
            throw new ValidationException("insufficient balance to perform the set transaction");
    }

    void nullValues(){
        if (this.amount==0) throw new ValidationException("amount must not be 0");
        if (this.fromAccount == null) throw new ValidationException("Account from must not be null");
        if (this.toAccount == null) throw new ValidationException("Account To must not be null");
    }



}
