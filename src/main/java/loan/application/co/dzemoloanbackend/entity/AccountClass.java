package loan.application.co.dzemoloanbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_account_class")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountClass extends BaseEntity {

    private String classCode;
    private String className;
    private double accountLimit;
    private boolean allowNegatives;
    private double transactionLimit;
    private String currency;

}
