package com.erp.ERPSystem.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "customer_uuid"))
public class CustomerEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private String email;

}
