package co.sofka.springboot.JPA.Hibernate.repository;

import co.sofka.springboot.JPA.Hibernate.model.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeJpaRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeid(String employeeid);
    List<Employee> findByLastName(String lastName);
}
