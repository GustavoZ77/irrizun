package zuun.tech.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuun.tech.budget.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
