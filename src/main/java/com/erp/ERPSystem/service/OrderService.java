package com.erp.ERPSystem.service;

import com.erp.ERPSystem.database.entity.CustomerEntity;
import com.erp.ERPSystem.database.entity.OrderEntity;
import com.erp.ERPSystem.database.entity.ProductEntity;
import com.erp.ERPSystem.database.repository.OrderRepository;
import com.erp.ERPSystem.database.repository.ProductRepository;
import com.erp.ERPSystem.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    /*
    Create,update,delete ve get methodlarının yanı sıra şiparişe ürün ekleme, şipariş listesinin toplam ücretini hesaplama
    ve şipariş durumunun onaylanma veya reddedilme durumunun kontrolü için methodlar eklendi.
     */

    public boolean createCustomerOrder(CustomerEntity customer, List<ProductEntity> productEntityList)
    {
        if (customer == null || productEntityList == null)
            return false;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customer);
        orderEntity.setProductList(productEntityList);
        orderRepository.save(orderEntity);
        return true;
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getByStatus(StatusEnum statusEnum) {
        return orderRepository.getOrderEntitiesByStatus(statusEnum);
    }

    public OrderEntity getByUuid(UUID uuid){
        return orderRepository.findByUuid(uuid);
    }

    public boolean deleteOrderEntity(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            orderRepository.deleteByUuid(uuid);
            return true;
        }
    }

    public boolean updateOrderEntity(UUID uuid, OrderEntity orderEntity) {
        if (uuid == null || orderEntity == null)
            return false;
        else {
            OrderEntity existingOrder = orderRepository.findByUuid(uuid);
            if (existingOrder == null)
                return false;
            existingOrder.setCustomer(orderEntity.getCustomer());
            existingOrder.setProductList(orderEntity.getProductList());
            existingOrder.setStatus(orderEntity.getStatus());
            orderRepository.save(existingOrder);
            return true;
        }
    }

    public boolean addProductToOrder(UUID orderUuid, UUID productUuid) {
        if (orderUuid == null || productUuid == null)
            return false;
        else {
            OrderEntity existingOrder = orderRepository.findByUuid(orderUuid);
            if (existingOrder == null)
                return false;
            ProductEntity product = productRepository.findByUuid(productUuid);
            if (existingOrder.getProductList().contains(product))
            {
                for (ProductEntity productEntity : existingOrder.getProductList()) {
                    if (productEntity.getUuid().equals(productUuid))
                        productEntity.setOrderCount(productEntity.getOrderCount() + 1);
                }
            } else
                existingOrder.getProductList().add(product);
            orderRepository.save(existingOrder);
            return true;
        }
    }

    public void calculateTotalPrice(UUID uuid) {
        OrderEntity orderEntity = orderRepository.findByUuid(uuid);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductEntity productEntity : orderEntity.getProductList()) {
            BigDecimal productPrice = productEntity.getPrice().multiply(BigDecimal.valueOf(productEntity.getOrderCount()));
            totalPrice = totalPrice.add(productPrice);
        }
        orderEntity.setTotalPrice(totalPrice);
        orderRepository.save(orderEntity);
    }

    public OrderEntity controlOrderStatus(UUID orderUuid) {
        OrderEntity order = orderRepository.findByUuid(orderUuid);
        if (order == null)
            return null;
        if (order.getStatus() == StatusEnum.WAITING) {
            for (ProductEntity productEntity : order.getProductList()) {
                if (productEntity.getStock() < productEntity.getOrderCount()) {
                    order.setStatus(StatusEnum.REJECTED);
                    orderRepository.save(order);
                    return order;
                } else {
                    order.setStatus(StatusEnum.APPROVED);
                    productEntity.setStock(productEntity.getStock() - productEntity.getOrderCount());
                    orderRepository.save(order);
                }
            }
        }
        calculateTotalPrice(orderRepository.findByUuid(orderUuid).getUuid());
        return order;
    }
}

