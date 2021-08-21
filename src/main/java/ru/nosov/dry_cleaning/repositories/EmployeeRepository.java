package ru.nosov.dry_cleaning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nosov.dry_cleaning.entities.ClientEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<ClientEntity, Long> {
}
