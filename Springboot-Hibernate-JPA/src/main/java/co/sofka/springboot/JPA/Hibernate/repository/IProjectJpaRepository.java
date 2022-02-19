package co.sofka.springboot.JPA.Hibernate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.sofka.springboot.JPA.Hibernate.model.Project;

@Repository
public interface IProjectJpaRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(Long id);
    List<Project> findByName(String name);
}
