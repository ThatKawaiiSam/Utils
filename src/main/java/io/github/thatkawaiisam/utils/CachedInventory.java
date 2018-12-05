package io.github.thatkawaiisam.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter @Setter
public class CachedInventory {

    private final ItemStack[] cachedInventory, cachedArmor;
    private final UUID inventoryUUID = UUID.randomUUID();
    private final double health, food;

    /**
     * Cached Inventory Class
     */
    public CachedInventory(Player player) {
        cachedInventory = player.getInventory().getContents();
        cachedArmor = player.getInventory().getArmorContents();
        health = player.getHealth();
        food = player.getFoodLevel();
    }
}
