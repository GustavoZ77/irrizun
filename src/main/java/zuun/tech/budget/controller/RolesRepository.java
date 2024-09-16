package zuun.tech.budget.controller;


import org.springframework.data.repository.CrudRepository;
import zuun.tech.budget.domain.Roles;

public interface RolesRepository extends CrudRepository<Roles,Long> {

}