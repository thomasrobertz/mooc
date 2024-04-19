package de.robertz.mooc.security.productservice.repository;

import de.robertz.mooc.security.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByName(String name);
}
