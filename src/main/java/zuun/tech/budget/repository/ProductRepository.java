package zuun.tech.budget.repository;

import org.springframework.data.repository.CrudRepository;
import zuun.tech.budget.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
