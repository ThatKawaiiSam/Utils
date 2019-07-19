package io.github.thatkawaiisam.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {

    private Material material;
    private Short durability;
    private String title;
    private int amount = 1;
    private List<String> lores;
    private byte materialData;
    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();
    private ItemStack itemStack;

    public ItemBuilder() {
        this.itemStack = new ItemStack(Material.AIR);
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder material(Material material){
        this.material = material;
        return this;
    }

    public ItemBuilder durability(short durability){
        this.durability = durability;
        return this;
    }

    public ItemBuilder title(String title){
        this.title = title;
        return this;
    }

    public ItemBuilder amount(int amount){
        this.amount = amount;
        return this;
    }

    public ItemBuilder lores(List<String> lores){
        this.lores = lores;
        return this;
    }

    public ItemBuilder enchantment(final Enchantment enchantment, final int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder enchantment(final Enchantment enchantment) {
        enchantment(enchantment, 1);
        return this;
    }

    public ItemBuilder clearLore() {
        lores.clear();
        return this;
    }

    public ItemBuilder data(final int data) {
        this.materialData = (byte) data;
        return this;
    }

    public ItemBuilder clearEnchantments() {
        enchantments.clear();
        return this;
    }

    public ItemStack build(){
        ItemStack itemStack = this.itemStack;
        if (this.material != null) {
            itemStack.setType(this.material);
        }
        //TODO Enchantments
        for (Enchantment enchantment : enchantments.keySet()) {
            itemStack.addUnsafeEnchantment(enchantment, enchantments.get(enchantment));
        }
        ItemMeta meta = itemStack.getItemMeta();
        if (this.amount > 0)
            itemStack.setAmount(this.amount);
        if (this.durability != null)
            itemStack.setDurability(this.durability);
        if (this.title != null)
            meta.setDisplayName(MessageUtility.formatMessage("&r" + this.title));
        if (this.lores != null && this.lores.size() > 0)
            meta.setLore(MessageUtility.formatMessages(this.lores));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

//    https://www.spigotmc.org/threads/converting-player-inventory-to-json-using-gson-is-throwing-illegalargumentexception.73522/
    public static String itemTo64(ItemStack stack) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(stack);

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e) {
            throw new IllegalStateException("Unable to save item stack.", e);
        }
    }

    public static ItemStack itemFrom64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            try {
                return (ItemStack) dataInput.readObject();
            } finally {
                dataInput.close();
            }
        }
        catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}
