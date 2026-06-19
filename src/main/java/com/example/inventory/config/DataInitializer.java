package com.example.inventory.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.inventory.dto.InventoryInputDto;
import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataInitializer implements CommandLineRunner{

    private final InventoryRepository inventoryRepository;
    private final ObjectMapper objectMapper;

    public DataInitializer (InventoryRepository inventoryRepository, ObjectMapper objectMapper){
        this.inventoryRepository = inventoryRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
        if(inventoryRepository.count()>0) return;

        InputStream inputStream = getClass()
            .getClassLoader().getResourceAsStream("inventory-input.json");

        List<InventoryInputDto> inputs = 
            objectMapper.readValue(inputStream, new TypeReference<List<InventoryInputDto>>(){});

        List<Inventory> inventories = inputs.stream()
            .map(dto -> {
                Inventory inventory = new Inventory();
                inventory.setProductId(dto.productId());
                inventory.setStock(dto.stock());
                return inventory;
            }).toList();

        inventoryRepository.saveAll(inventories);
    }

}
