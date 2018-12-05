package io.github.thatkawaiisam.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class PlayerHeadUtility {

    /* Pre Defined */
    private Material material = Material.SKULL_ITEM;
    private Short durability = (short) 3;
    private int amount = 1;

    /* Editable */
    private String title;
    private String owner = "steve";
    private List<String> lores;

    public PlayerHeadUtility title(String title){
        this.title = title;
        return this;
    }

    public PlayerHeadUtility owner(String owner) {
        this.owner = owner;
        return this;
    }

    public PlayerHeadUtility lores(List<String> lores){
        this.lores = lores;
        return this;
    }

    public ItemStack build(){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        itemStack.setAmount(1);
        itemStack.setDurability((short) 3);
        if (this.title != null) {
            meta.setDisplayName(MessageUtility.formatMessage("&r" + this.title));
        }
        if (this.lores != null && this.lores.size() > 0) {
            meta.setLore(MessageUtility.formatMessages(this.lores));
        }
        meta.setOwner(owner);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
