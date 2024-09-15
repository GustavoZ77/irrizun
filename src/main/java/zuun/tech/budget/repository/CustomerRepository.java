package zuun.tech.budget.repository;

import org.springframework.data.repository.CrudRepository;
import zuun.tech.budget.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
