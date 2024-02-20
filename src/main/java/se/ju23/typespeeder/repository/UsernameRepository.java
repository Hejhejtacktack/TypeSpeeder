package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.model.Username;

import java.util.Optional;

@Repository
public interface UsernameRepository extends JpaRepository<Username, Integer> {
    Optional<Username> findByValue(String value);
    boolean existsByValue(String username);
}
