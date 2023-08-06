package com.erp.ERPSystem.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {
    private UUID uuid;
    private String name;
    private Boolean isTaxApplied;
    private BigDecimal price;
    private BigDecimal nonTaxAppliedPrice = BigDecimal.ZERO;
    private Integer stock;
    private Tax tax;
    private Order order;
    private int orderCount;

    public Product() {
        this.uuid = UUID.randomUUID();
    }
}
