package de.robertz.mooc.security.couponservice.repository;

import de.robertz.mooc.security.couponservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
	Coupon findByCode(String code);
}
