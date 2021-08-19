package ru.nosov.dry_cleaning.repositories;

import org.springframework.stereotype.Repository;
import ru.nosov.dry_cleaning.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
