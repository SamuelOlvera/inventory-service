package com.example.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.model.InventoryResponse;
import com.example.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController (InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse>getInventory(@PathVariable String productId){
        InventoryResponse inventoryResponse = inventoryService.getInventoryByProduct(productId);

        return ResponseEntity.ok(inventoryResponse);
    }

}
