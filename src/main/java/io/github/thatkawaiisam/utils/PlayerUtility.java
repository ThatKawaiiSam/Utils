package io.github.thatkawaiisam.utils;

import io.github.thatkawaiisam.utils.playerversion.PlayerVersion;
import io.github.thatkawaiisam.utils.playerversion.PlayerVersionHandler;
import io.github.thatkawaiisam.utils.serverversion.ServerVersionHandler;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class PlayerUtility {

    /**
     * Get the Player's Language Locale
     */
    public static String getPlayerLanguage(Player player) {
        return ServerVersionHandler.version.getPlayerLanguage(player);
    }

    /**
     * Clears the arrows from the Player's body
     */
    public static void clearArrowsFromPlayer(Player player) {
        ServerVersionHandler.version.clearArrowsFromPlayer(player);
    }

    /**
     * Get the Player's current protocol
     */
    public static PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersionHandler.version.getPlayerVersion(player);
    }

    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    public static void healPlayer(Player player) {
        player.setHealth(player.getMaxHealth());
    }

    public static void feedPlayer(Player player) {
        player.setFoodLevel(20);
        player.setExhaustion(0);
    }

    public static void clearLevels(Player player) {
        player.setLevel(0);
        player.setExp(0);
    }

    public static void clearEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

}
