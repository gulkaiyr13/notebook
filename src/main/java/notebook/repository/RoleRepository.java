package notebook.repository;

import notebook.entity.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role, Integer> {
    boolean existsByName(String name);
    Role findByName(String name);
}
