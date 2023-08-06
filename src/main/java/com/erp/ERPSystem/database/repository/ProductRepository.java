package com.erp.ERPSystem.database.repository;

import com.erp.ERPSystem.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    void deleteByUuid(UUID uuid);

    ProductEntity findByUuid(UUID uuid);

    List<ProductEntity> findAllByNameContainsIgnoreCase(String name);


}
