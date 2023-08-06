package com.erp.ERPSystem.controller;

import com.erp.ERPSystem.database.entity.OrderEntity;
import com.erp.ERPSystem.service.OrderService;
import com.erp.ERPSystem.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get/{uuid}")
    public ResponseEntity<OrderEntity> getByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(orderService.getByUuid(uuid), HttpStatus.OK);
    }

    @GetMapping("getByStatus/{status}")
    public ResponseEntity<List<OrderEntity>> getByStatus(@PathVariable String status) {
        return new ResponseEntity<>(orderService.getByStatus(StatusEnum.fromString(status)), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderEntity order) {
        return new ResponseEntity<>(orderService.createCustomerOrder(order.getCustomer(),
                order.getProductList()), HttpStatus.CREATED);
    }

    @PutMapping("update/{uuid}")
    public ResponseEntity<Boolean> updateOrder(@PathVariable UUID uuid, @RequestBody OrderEntity order) {
        return new ResponseEntity<>(orderService.updateOrderEntity(uuid, order), HttpStatus.OK);
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable UUID uuid) {
        return new ResponseEntity<>(orderService.deleteOrderEntity(uuid), HttpStatus.OK);
    }


    @PostMapping("add/{orderuuid}/{productuuid}")
    public ResponseEntity<Boolean> addProductToOrder(@PathVariable UUID orderuuid,
                                                     @PathVariable UUID productuuid)
    {

        return new ResponseEntity<>(orderService.addProductToOrder(orderuuid, productuuid), HttpStatus.OK);
    }

    @PostMapping("approve/{customerOrderUuid}")
    public ResponseEntity<OrderEntity> approveOrder(@PathVariable UUID customerOrderUuid) {
        return new ResponseEntity<>(orderService.controlOrderStatus(customerOrderUuid)
                , HttpStatus.OK);
    }
}

