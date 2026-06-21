package com.example.inventory.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.inventory.entity.Inventory;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.model.InventoryResponse;
import com.example.inventory.repository.InventoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void getInventoryByProduct_whenExists_returnsResponse(){
        Inventory inventory = new Inventory();
        inventory.setProductId("EXT-001");
        inventory.setStock(10);

        when(inventoryRepository.findByProductId("EXT-001"))
            .thenReturn(Optional.of(inventory));

            InventoryResponse inventoryResponse = inventoryService.getInventoryByProduct("EXT-001");

            assertThat(inventoryResponse.productId()).isEqualTo("EXT-001");
            assertThat(inventoryResponse.stock()).isEqualTo(10);
    }

    @Test
    void getInventoryByProduc_whenNotFound_throwsResourceNotFoundException(){
        when (inventoryRepository.findByProductId("EXT-999"))
            .thenThrow(new ResourceNotFoundException ("Inventory not found for product: EXT-999"));

            assertThatThrownBy(()->inventoryService.getInventoryByProduct("EXT-999"))
                .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("EXT-999");
    }
}
