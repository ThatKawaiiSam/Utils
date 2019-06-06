package io.github.thatkawaiisam.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter @Setter
public class CachedInventory {

    private final ItemStack[] cachedInventory, cachedArmor;
    private final double health;
    private final int totalExperience, food;
    private final float exp;

    public CachedInventory(Player player) {
        cachedInventory = player.getInventory().getContents();
        cachedArmor = player.getInventory().getArmorContents();
        health = player.getHealth();
        food = player.getFoodLevel();
        totalExperience = player.getTotalExperience();
        exp = player.getExp();
    }

    //From player to cached inventory.
    public static CachedInventory fromPlayer(Player player) {
        return new CachedInventory(player);
    }

    //From Inventory to player.
    public void applyToPlayer(Player player) {
        player.getInventory().setContents(this.cachedInventory);
        player.getInventory().setArmorContents(this.cachedArmor);
        player.setHealth(this.health);
        player.setFoodLevel(this.food);
        player.setTotalExperience(this.totalExperience);
        player.setExp(this.exp);
    }
}
