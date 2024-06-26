package com.example.se_practice.common;

import com.example.se_practice.pojo.inventory;
import com.example.se_practice.pojo.item_attribute;
import com.example.se_practice.pojo.item_effect;

import java.util.List;

public class ItemInfo {
    private int totalweight;

    public int getTotalweight() {
        return totalweight;
    }

    private List<inventory> Inventory;

    private List<item_attribute> ItemAttributes;
    private List<item_effect> ItemEffects;

    public ItemInfo(int totalweight, List<inventory> Inventory, List<item_attribute> ItemAttributes, List<item_effect> ItemEffects) {
        this.totalweight=totalweight;
        this.Inventory = Inventory;
        this.ItemAttributes = ItemAttributes;
        this.ItemEffects = ItemEffects;
    }

    public List<inventory> getInventory() {
        return Inventory;
    }

    public void setInventory(List<inventory> inventory) {
        Inventory = inventory;
    }

    public List<item_attribute> getItemAttributes() {
        return ItemAttributes;
    }

    public void setItemAttributes(List<item_attribute> itemAttributes) {
        ItemAttributes = itemAttributes;
    }

    public List<item_effect> getItemEffects() {
        return ItemEffects;
    }

    public void setItemEffects(List<item_effect> itemEffects) {
        ItemEffects = itemEffects;
    }
}
