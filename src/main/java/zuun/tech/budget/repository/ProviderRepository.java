package zuun.tech.budget.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zuun.tech.budget.domain.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
}