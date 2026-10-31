package com.grocery.service;

import com.grocery.model.Inventory;
import com.grocery.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public List<Inventory> getAllStock() {
        return repository.findAll();
    }

    public Inventory addStock(Inventory item) {
        if (item.getProductId() == null ||
            item.getQuantity() < 0) {
            throw new IllegalArgumentException("Invalid stock details");
        }

        return repository.save(item);
    }
}