package com.grocery.controller;

import com.grocery.model.CustomerOrder;
import com.grocery.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerOrder> getOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public CustomerOrder placeOrder(
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return service.placeOrder(productId, quantity);
    }
}