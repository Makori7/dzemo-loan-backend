package loan.application.co.dzemoloanbackend.repository;

import loan.application.co.dzemoloanbackend.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findFirstByMobileNumber(String mobileNumber);
    Optional<Account> findFirstByAccountNumber(String accountNumber);
}
