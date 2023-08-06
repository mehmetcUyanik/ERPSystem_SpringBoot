package com.erp.ERPSystem.database.repository;

import com.erp.ERPSystem.database.entity.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaxRepository extends JpaRepository<TaxEntity, Long> {
    List<TaxEntity> findAllByNameContainsIgnoreCase(String name);

    void deleteByUuid(UUID uuid);

    TaxEntity findByUuid(UUID uuid);

}
