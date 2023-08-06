package com.erp.ERPSystem.service;

import com.erp.ERPSystem.database.entity.ProductEntity;
import com.erp.ERPSystem.database.entity.TaxEntity;
import com.erp.ERPSystem.database.repository.ProductRepository;
import com.erp.ERPSystem.database.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TaxRepository taxRepository;

    /*
    Create,update,delete ve get methodlarının yanı sıra ürün için vergili/vergisiz fiyat düzenleme methodu eklendi.
     */

    public boolean createProduct(String name, boolean isTaxApplied, BigDecimal price, Integer stock, TaxEntity tax) {
        if (name == null || price == null || stock == null || tax == null) {
            return false;
        } else {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(name);
            productEntity.setIsTaxApplied(isTaxApplied);
            productEntity.setPrice(price);
            productEntity.setStock(stock);
            productEntity.setTax(taxRepository.findByUuid(tax.getUuid()));
            productEntity.setOrderCount(1);
            taxTruePrice(productEntity);
            productRepository.save(productEntity);
            return true;
        }
    }

    public boolean updateProduct(UUID uuid, ProductEntity productEntity) {
        if (productEntity == null)
            return false;
        else {
            ProductEntity existingProduct = productRepository.findByUuid(uuid);
            if (existingProduct == null)
                return false;
            existingProduct.setName(productEntity.getName());
            existingProduct.setIsTaxApplied(productEntity.getIsTaxApplied());
            existingProduct.setPrice(productEntity.getPrice());
            existingProduct.setNonTaxAppliedPrice(productEntity.getNonTaxAppliedPrice());
            existingProduct.setStock(productEntity.getStock());
            existingProduct.setTax(productEntity.getTax());
            productRepository.save(existingProduct);
            return true;
        }
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public boolean deleteProduct(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            productRepository.deleteByUuid(uuid);
            return true;
        }
    }

    public ProductEntity getByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid);
    }

    public List<ProductEntity> getAllByNameContainsIgnoreCase(String name) {
        return productRepository.findAllByNameContainsIgnoreCase(name);
    }

    public void taxTruePrice(ProductEntity product) {
        BigDecimal tax = product.getTax().getPercent();
        BigDecimal price = product.getPrice();
        BigDecimal totalPrice;
        BigDecimal taxPrice;
        if (!product.getIsTaxApplied()) {
            product.setNonTaxAppliedPrice(price);
            taxPrice = (price.multiply(tax)).divide(new BigDecimal(100), MathContext.DECIMAL32);
            totalPrice = price.add(taxPrice);
            product.setPrice(totalPrice);
        } else {
            totalPrice = price;
            BigDecimal nonTaxPrice = (totalPrice.multiply(new BigDecimal(100))).divide((new BigDecimal(100)).add(tax), MathContext.DECIMAL32);
            product.setNonTaxAppliedPrice(nonTaxPrice);
        }
    }

}
