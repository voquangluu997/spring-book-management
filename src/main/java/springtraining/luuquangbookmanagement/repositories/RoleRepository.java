package springtraining.luuquangbookmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springtraining.luuquangbookmanagement.repositories.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
