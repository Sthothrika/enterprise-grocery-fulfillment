package com.grocery.service;

import com.grocery.model.*;
import com.grocery.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final ProductRepository products;
    private final InventoryRepository inventory;
    private final OrderRepository orders;

    public OrderService(ProductRepository products,
                        InventoryRepository inventory,
                        OrderRepository orders) {
        this.products = products;
        this.inventory = inventory;
        this.orders = orders;
    }

    public List<CustomerOrder> getAllOrders() {
        return orders.findAll();
    }

    @Transactional
    public CustomerOrder placeOrder(Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero");
        }

        Product product = products.findById(productId)
            .orElseThrow(() ->
                new IllegalArgumentException("Product not found"));

        Inventory stock = inventory.findByProductId(productId)
            .orElseThrow(() ->
                new IllegalArgumentException("Stock not found"));

        if (stock.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        double total = product.getPrice() * quantity;

        stock.setQuantity(stock.getQuantity() - quantity);
        inventory.save(stock);

        CustomerOrder order = new CustomerOrder(
            productId, quantity, total, "CONFIRMED");

        return orders.save(order);
    }
}