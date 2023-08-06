package com.erp.ERPSystem.database.repository;


import com.erp.ERPSystem.database.entity.OrderEntity;
import com.erp.ERPSystem.util.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    void deleteByUuid(UUID uuid);

    OrderEntity findByUuid(UUID uuid);

    List<OrderEntity> getOrderEntitiesByStatus(StatusEnum statusEnum);
}
