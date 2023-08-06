package com.erp.ERPSystem.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {
    private UUID uuid;
    private String name;
    private String email;
    public Customer() {
        this.uuid = UUID.randomUUID();
    }
}
