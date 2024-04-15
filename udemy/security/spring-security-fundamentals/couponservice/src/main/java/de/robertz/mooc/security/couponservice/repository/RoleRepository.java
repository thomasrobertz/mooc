package de.robertz.mooc.security.couponservice.repository;

import de.robertz.mooc.security.couponservice.model.Role;
import de.robertz.mooc.security.couponservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}