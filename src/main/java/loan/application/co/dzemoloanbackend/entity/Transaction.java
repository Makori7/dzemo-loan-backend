package loan.application.co.dzemoloanbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction extends BaseEntity{

    private double amount;
    private String accountNumber;
    private String customerNumber;
    private char debitCredit;
    private String transactionReference;
    private String externalReference; // incase of third party reference e.g Mpesa or Pesalink
    private double balanceBefore;
    private boolean reversed;
    private String narration;
    private String customerNames;

}
