package com.erp.ERPSystem.controller;

import com.erp.ERPSystem.database.entity.TaxEntity;
import com.erp.ERPSystem.model.Tax;
import com.erp.ERPSystem.service.TaxService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tax")
public class TaxController {
    @Autowired
    TaxService taxService;

    @GetMapping
    public ResponseEntity<List<TaxEntity>> getAll() {
        return new ResponseEntity<>(taxService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<TaxEntity>> getAllByName(@PathVariable String name) {
        return new ResponseEntity<>(taxService.getAllByNameIContains(name), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @PutMapping("update/{uuid}")
    public ResponseEntity<Boolean> updateTaxByUuid(@PathVariable UUID uuid, @RequestBody Tax tax) {
        TaxEntity newTax = new TaxEntity();
        newTax.setName(tax.getName());
        newTax.setPercent(tax.getPercent());
        return new ResponseEntity<>(taxService.updateTax(uuid, newTax), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Boolean> deleteTaxByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(taxService.deleteTax(uuid), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Boolean> createTax(@RequestBody Tax tax) {
        return new ResponseEntity<>(taxService.createTax(tax.getName(), tax.getPercent()), HttpStatus.CREATED);
    }
}
