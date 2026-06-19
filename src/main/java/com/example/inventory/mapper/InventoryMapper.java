package com.example.inventory.mapper;

import com.example.inventory.entity.Inventory;
import com.example.inventory.model.InventoryResponse;

public class InventoryMapper {

    public static InventoryResponse toResponse(Inventory inventory){
        return new InventoryResponse(inventory.getProductId(), inventory.getStock());
    }
}
