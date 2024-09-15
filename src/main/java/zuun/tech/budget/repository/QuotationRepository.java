package zuun.tech.budget.repository;

import org.springframework.data.repository.CrudRepository;
import zuun.tech.budget.domain.Quotation;

public interface QuotationRepository extends CrudRepository<Quotation, Long> {
}
