package de.robertz.mooc.security.couponservice.repository;

import de.robertz.mooc.security.couponservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
