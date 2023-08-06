package com.erp.ERPSystem.database.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "tax_uuid"))
public class TaxEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private BigDecimal percent;
}
