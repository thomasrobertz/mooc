package de.robertz.mooc.security.productservice.repository;

import java.util.Optional;

import de.robertz.mooc.security.productservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
