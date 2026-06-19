package com.example.inventory.service;

import org.springframework.stereotype.Service;

import com.example.inventory.exception.ErrorMessages;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.mapper.InventoryMapper;
import com.example.inventory.model.InventoryResponse;
import com.example.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService (InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }
    public InventoryResponse getInventoryByProduct(String productId){

        return inventoryRepository.findByProductId(productId)
            .map(InventoryMapper::toResponse)
            .orElseThrow(()-> new ResourceNotFoundException(
                String.format(ErrorMessages.INVENTORY_NOT_FOUND_BY_PRODUCT, productId )
            ));
    }
}
