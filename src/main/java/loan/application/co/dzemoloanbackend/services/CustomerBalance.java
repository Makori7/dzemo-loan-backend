package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.entity.Account;
import lombok.Data;

@Data
public class CustomerBalance {

    private double amount;
    private boolean debit;
    private Account account;

    public CustomerBalance(double amount, boolean debit, Account account) {
        this.amount = amount;
        this.debit = debit;
        this.account = account;
    }

    Account updateBalances(){
        Account swapAccount = this.account;
        this.account.setActualBalance(( swapAccount.getActualBalance() - (debit ? this.amount : -1*this.amount)));
        this.account.setAvailableBalance((swapAccount.getAvailableBalance() - (debit ? this.amount : -1*this.amount)));

        return this.account;
    }


}
