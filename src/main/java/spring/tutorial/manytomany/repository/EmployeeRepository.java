package spring.tutorial.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.tutorial.manytomany.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
