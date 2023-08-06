package com.erp.ERPSystem.service;

import com.erp.ERPSystem.database.entity.TaxEntity;
import com.erp.ERPSystem.database.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class TaxService {
    @Autowired
    TaxRepository taxRepository;

    /*
    Create,update,delete ve get methodları eklendi.
     */

    public boolean createTax(String name, BigDecimal percent) {
        if (name == null || percent == null)
            return false;
        else {
            TaxEntity taxEntity = new TaxEntity();
            taxEntity.setName(name);
            taxEntity.setPercent(percent);
            taxRepository.save(taxEntity);
            return true;
        }
    }

    public List<TaxEntity> getAll() {
        return taxRepository.findAll();
    }

    public List<TaxEntity> getAllByNameIContains(String name) {
        return taxRepository.findAllByNameContainsIgnoreCase(name);
    }

    public boolean deleteTax(UUID uuid) {
        if (uuid == null)
            return false;
        taxRepository.deleteByUuid(uuid);
        return true;
    }

    public boolean updateTax(UUID uuid, TaxEntity taxEntity) {
        if (uuid == null || taxEntity == null)
            return false;
        else {
            TaxEntity existingTax = taxRepository.findByUuid(uuid);
            if (existingTax == null)
                return false;
            existingTax.setName(taxEntity.getName());
            existingTax.setPercent(taxEntity.getPercent());
            taxRepository.save(existingTax);
            return true;
        }
    }
}
