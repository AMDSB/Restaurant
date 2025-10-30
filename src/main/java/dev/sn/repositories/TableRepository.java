package dev.sn.repositories;

import dev.sn.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table , Long> {
}
