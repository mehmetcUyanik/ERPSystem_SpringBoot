package com.erp.ERPSystem.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "product_uuid"))
public class ProductEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private Boolean isTaxApplied;
    @Column
    private BigDecimal price;
    @Column
    private BigDecimal nonTaxAppliedPrice = BigDecimal.ZERO;
    @Column
    private Integer stock;
    @Column
    private int orderCount;
    @ManyToOne
    @JoinColumn(name = "tax_id", unique = false)
    private TaxEntity tax;

}
