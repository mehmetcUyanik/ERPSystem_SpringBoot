package com.erp.ERPSystem.controller;

import com.erp.ERPSystem.database.entity.ProductEntity;
import com.erp.ERPSystem.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get/{uuid}")
    public ResponseEntity<ProductEntity> getByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.getByUuid(uuid), HttpStatus.OK);
    }

    @GetMapping("getByName/{name}")
    public ResponseEntity<List<ProductEntity>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(productService.getAllByNameContainsIgnoreCase(name), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @GetMapping("delete/{uuid}")
    public ResponseEntity<Boolean> deleteByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.deleteProduct(uuid), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @PutMapping("update/{uuid}")
    public ResponseEntity<Boolean> updateByUuid(@PathVariable UUID uuid, @RequestBody ProductEntity product) {
        return new ResponseEntity<>(productService.updateProduct(uuid, product), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Boolean> create(@RequestBody ProductEntity product) {
        return new ResponseEntity<>(productService.createProduct(product.getName(), product.getIsTaxApplied(),
                product.getPrice(),product.getStock(),
                product.getTax()), HttpStatus.CREATED);
    }
}
