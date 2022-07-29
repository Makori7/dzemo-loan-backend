package loan.application.co.dzemoloanbackend.repository;

import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.services.CrudOperation;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {


}
