package com.example.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.entity.Inventory;
import java.util.List;


public interface InventoryRepository extends JpaRepository <Inventory, Long>{

    public Optional<Inventory> findByProductId(String productId);

}
