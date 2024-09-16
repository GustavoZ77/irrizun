package zuun.tech.budget.repository;


import org.springframework.data.repository.CrudRepository;
import zuun.tech.budget.domain.Roles;

public interface RolesRepository extends CrudRepository<Roles,Long> {

}