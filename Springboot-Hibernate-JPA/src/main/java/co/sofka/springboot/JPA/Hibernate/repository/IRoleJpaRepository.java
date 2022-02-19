package co.sofka.springboot.JPA.Hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.sofka.springboot.JPA.Hibernate.model.Role;

@Repository
public interface IRoleJpaRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
