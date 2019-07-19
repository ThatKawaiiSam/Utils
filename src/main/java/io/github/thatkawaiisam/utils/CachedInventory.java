package io.github.thatkawaiisam.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class CachedInventory {

    private ItemStack[] cachedInventory, cachedArmor;
    private double health = -1;
    private int totalExperience = -1, food = -1;
    private float exp = -1;

    public CachedInventory() {

    }

    public CachedInventory(Player player, boolean justContents) {
        cachedInventory = player.getInventory().getContents();
        cachedArmor = player.getInventory().getArmorContents();
        if (!justContents) {
            health = player.getHealth();
            food = player.getFoodLevel();
            totalExperience = player.getTotalExperience();
            exp = player.getExp();
        }
    }

    //From player to cached inventory.
    public static CachedInventory fromPlayer(Player player, boolean justContents) {
        return new CachedInventory(player, justContents);
    }

    //From Inventory to player.
    public void applyToPlayer(Player player, boolean justContents) {
        player.getInventory().setContents(this.cachedInventory);
        player.getInventory().setArmorContents(this.cachedArmor);

        if (!justContents) {
            if (health != -1) {
                player.setHealth(this.health);
            }
            if (this.food != -1) {
                player.setFoodLevel(this.food);
            }
            if (this.totalExperience != -1) {
                player.setTotalExperience(this.totalExperience);
            }
            if (this.exp != -1) {
                player.setExp(this.exp);
            }
        }
    }

    public static CachedInventory fromJson(JsonObject jsonObject) {
        CachedInventory cachedInventory = new CachedInventory();

        // Armor
        JsonArray armorArray = jsonObject.get("armor").getAsJsonArray();
        List<ItemStack> armor = new ArrayList<>();
        armorArray.forEach(armorElement -> {
            try {
                armor.add(ItemBuilder.itemFrom64(armorElement.getAsString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cachedInventory.setCachedArmor(armor.stream().toArray(ItemStack[]::new));

        // Inventory
        JsonArray invArray = jsonObject.get("inv").getAsJsonArray();
        List<ItemStack> inv = new ArrayList<>();
        invArray.forEach(invElement -> {
            try {
                inv.add(ItemBuilder.itemFrom64(invElement.getAsString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cachedInventory.setCachedInventory(inv.stream().toArray(ItemStack[]::new));

        // Health and Others
        if (jsonObject.has("health")) {
            cachedInventory.setHealth(jsonObject.get("health").getAsDouble());
        }
        if (jsonObject.has("totalExperience")) {
            cachedInventory.setHealth(jsonObject.get("totalExperience").getAsInt());
        }
        if (jsonObject.has("food")) {
            cachedInventory.setHealth(jsonObject.get("food").getAsInt());
        }
        if (jsonObject.has("exp")) {
            cachedInventory.setHealth(jsonObject.get("exp").getAsFloat());
        }

        return cachedInventory;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        if (this.health != -1) {
            jsonObject.addProperty("health", health);
        }
        if (this.totalExperience != -1) {
            jsonObject.addProperty("totalExperience", totalExperience);
        }
        if (this.food != -1) {
            jsonObject.addProperty("food", food);
        }
        if (this.exp != -1) {
            jsonObject.addProperty("exp", exp);
        }

        JsonArray armorArray = new JsonArray();
        for (ItemStack itemStack : cachedArmor) {
            armorArray.add(new JsonPrimitive(ItemBuilder.itemTo64(itemStack)));
        }
        jsonObject.add("armor", armorArray);

        JsonArray invArray = new JsonArray();
        for (ItemStack itemStack : cachedInventory){
            invArray.add(new JsonPrimitive(ItemBuilder.itemTo64(itemStack)));
        }
        jsonObject.add("inv", invArray);

        return jsonObject;
    }
}
