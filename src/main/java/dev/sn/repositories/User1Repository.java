package dev.sn.repositories;

import dev.sn.entities.User1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User1Repository  extends JpaRepository<User1, Long> {
//    User1 findByUsername(String username);
//    boolean existByUsername(String username);
    Optional<User1> findByEmail(String email);
}
