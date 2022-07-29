package loan.application.co.dzemoloanbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_account")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account extends BaseEntity{

    @ManyToOne
    private Customer customer;
    @Column(unique = true)
    private String accountNumber;
    private double actualBalance;
    private double availableBalance;
    private boolean blocked;
    private String mobileNumber; // added to optimize search operation

    @OneToOne
    private AccountClass accountClass;

    @PrePersist
    void createAccountNumber(){

        this.accountNumber = String.valueOf(100000000+getId());
    }
}
