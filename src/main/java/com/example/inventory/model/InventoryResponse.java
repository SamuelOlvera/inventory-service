package com.example.inventory.model;

public record InventoryResponse(
    String productId,

    int stock
) {}
