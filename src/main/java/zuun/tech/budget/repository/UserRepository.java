package zuun.tech.budget.repository;

import org.springframework.data.repository.CrudRepository;
import zuun.tech.budget.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
