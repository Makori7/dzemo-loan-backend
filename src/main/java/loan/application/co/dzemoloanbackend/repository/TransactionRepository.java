package loan.application.co.dzemoloanbackend.repository;

import loan.application.co.dzemoloanbackend.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
