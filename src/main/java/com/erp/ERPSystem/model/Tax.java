package com.erp.ERPSystem.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Tax {
    private UUID uuid;
    private String name;
    private BigDecimal percent;

    public Tax() {
        this.uuid = UUID.randomUUID();
    }
}
