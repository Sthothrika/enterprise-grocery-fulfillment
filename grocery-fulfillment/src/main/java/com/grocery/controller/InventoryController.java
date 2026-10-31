package com.grocery.controller;

import com.grocery.model.Inventory;
import com.grocery.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventory> getStock() {
        return service.getAllStock();
    }

    @PostMapping
    public Inventory addStock(@RequestBody Inventory item) {
        return service.addStock(item);
    }
}