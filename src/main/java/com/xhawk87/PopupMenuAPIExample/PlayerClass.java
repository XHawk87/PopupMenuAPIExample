/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhawk87.PopupMenuAPIExample;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * PlayerClass
 *
 * @author XHawk87
 */
public enum PlayerClass {

    SWORDSMAN("Swordsman",
    "You are skilled with the sword, and carry yours with you wherever you go. It is no surprise then that you have it with you when you awake in this strange new world",
    new MaterialData(Material.IRON_SWORD),
    new ItemStack(Material.IRON_SWORD, 1)),
    ARCHER("Archer",
    "You are skilled with the bow, and never leave yours behind with at least one quiver of arrows. This may aid you as you awake in this strange new world",
    new MaterialData(Material.BOW),
    new ItemStack(Material.BOW, 1),
    new ItemStack(Material.ARROW, 64)),
    MINER("Miner",
    "You are skilled with the pickaxe, and never leave home without one and a handful of torches if you happen to see a cave in your travels. You count yourself fortunate that it is still on you when you awake in this strange new world",
    new MaterialData(Material.IRON_PICKAXE),
    new ItemStack(Material.IRON_PICKAXE, 1),
    new ItemStack(Material.TORCH, 64)),
    BREWER("Brewer",
    "You are a skilled potions master, and never go out without your portable brewing kit and a handful of ingredients. This may serve you well as you begin to explore this strange new world",
    new MaterialData(Material.BREWING_STAND_ITEM),
    new ItemStack(Material.BREWING_STAND_ITEM, 1),
    new ItemStack(Material.GLASS_BOTTLE, 15),
    new ItemStack(Material.NETHER_STALK, 64),
    new ItemStack(Material.GLOWSTONE_DUST, 64),
    new ItemStack(Material.SOUL_SAND, 64),
    new ItemStack(Material.REDSTONE, 64),
    new ItemStack(Material.WOOD_PICKAXE, 1));
    private final String displayName;
    private final String description;
    private final MaterialData icon;
    private final ItemStack[] items;

    private PlayerClass(String displayName, String description, MaterialData icon, ItemStack... items) {
        this.displayName = displayName;
        this.description = description;
        this.icon = icon;
        this.items = items;
    }

    public void addStartingItems(Player player) {
        for (ItemStack item : items) {
            player.getInventory().addItem(item.clone());
        }
    }

    public MaterialData getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
