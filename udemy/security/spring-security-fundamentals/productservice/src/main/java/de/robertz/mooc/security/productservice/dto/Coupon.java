package de.robertz.mooc.security.productservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Coupon {
	private long id;
	private String code;
	private BigDecimal discount;
	private String expiry;
}
