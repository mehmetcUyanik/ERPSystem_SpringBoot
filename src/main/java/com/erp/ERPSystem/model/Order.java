package com.erp.ERPSystem.model;

import com.erp.ERPSystem.util.StatusEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private UUID uuid;
    private Customer customer;
    private List<Product> productList = new ArrayList<>();
    private StatusEnum status = StatusEnum.WAITING;

    public Order() {
        this.uuid = UUID.randomUUID();
    }
}