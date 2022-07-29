package loan.application.co.dzemoloanbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer extends BaseEntity{

        private String firstName;
        private String lastName;
        @Column(unique = true)
        private String phoneNumber;
        private boolean active;
        @Column(unique = true)
        private String nationalId;

        @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
        private Set<Account> accounts;

        Account getPrimaryAccount(){

                List<Account> c = accounts.stream().filter(
                        account -> account.getAccountClass().getClassCode().equalsIgnoreCase("200")).collect(Collectors.toList());
                // since its only one primary account we only fetch one

                return c.get(0);
        }

        List<Account> getLoanAccounts(){

                return accounts.stream().filter(
                        account -> account.getAccountClass().getClassCode()
                                .equalsIgnoreCase("205")).collect(Collectors.toList());

        }
}
