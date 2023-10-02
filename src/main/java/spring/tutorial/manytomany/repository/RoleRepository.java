package spring.tutorial.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.tutorial.manytomany.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
