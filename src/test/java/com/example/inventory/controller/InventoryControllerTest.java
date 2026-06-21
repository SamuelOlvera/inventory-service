package com.example.inventory.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.model.InventoryResponse;
import com.example.inventory.service.InventoryService;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventoryService inventoryService;

    @Test
    void getInventory_whenProductExists_return200WithStock() throws Exception{
        InventoryResponse mockInventoryResponse = new InventoryResponse("EXT-001", 10);

        when(inventoryService.getInventoryByProduct("EXT-001")).thenReturn(mockInventoryResponse);

        mockMvc.perform(get("/api/v1/inventory/EXT-001"))
            .andExpect(status().isOk())
            .andExpect(jsonPath(("$.productId")).value("EXT-001"))
            .andExpect(jsonPath(("$.stock")).value(10));
    }

    @Test
    void getinventory_whenProductNotFound_return404() throws Exception{
        when(inventoryService.getInventoryByProduct(("EXT-999")))
            .thenThrow(new ResourceNotFoundException("Inventory not found for product: EXT-999"));

        mockMvc.perform(get("/api/v1/inventory/EXT-999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath(("$.message")).value("Inventory not found for product: EXT-999"))
            .andExpect(jsonPath(("$.status")).value(404));
    }
    
}
