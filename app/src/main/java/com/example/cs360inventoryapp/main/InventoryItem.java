package com.example.finalsubmission.main;

public class InventoryItem {

    private int id;
    private String description;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryItem(int id, String description, int quantity)
    {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
    }
}
