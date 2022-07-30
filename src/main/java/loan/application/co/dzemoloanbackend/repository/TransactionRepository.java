package loan.application.co.dzemoloanbackend.repository;

import loan.application.co.dzemoloanbackend.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Page<Transaction> findAllByCreatedOnGreaterThanAndCreatedOnLessThanAndAccountNumber(LocalDateTime from, LocalDateTime to, String account,
                                                                                        Pageable pageable);
}
