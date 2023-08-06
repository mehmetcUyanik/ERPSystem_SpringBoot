package com.erp.ERPSystem.database.entity;

import com.erp.ERPSystem.util.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "order_uuid"))
public class OrderEntity extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "customer_id", unique = false)
    private CustomerEntity customer;

    @ManyToMany
    @JoinColumn(name = "product_id", unique = false)
    private List<ProductEntity> productList = new ArrayList<>();

    @Column
    private StatusEnum status = StatusEnum.WAITING;

    @Column
    private BigDecimal totalPrice;
}
