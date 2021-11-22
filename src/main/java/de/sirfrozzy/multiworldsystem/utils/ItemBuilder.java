package de.sirfrozzy.multiworldsystem.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    public String getName() {
        return name;
    }

    public Material getType() {
        return type;
    }

    public List<String> getLore() {
        return lore;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean getGlowing() {
        return glowing;
    }

    private String name;
    private Material type;
    private List<String> lore;
    private Integer amount;
    private Boolean glowing;

    public ItemBuilder(Material type) {
        this.type = type;
        this.name = type.name();
        this.lore = new ArrayList<>();
        this.amount = 1;
        this.glowing = false;
    }

    public ItemBuilder(Material type, String name) {
        this.type = type;
        this.name = name;
        this.lore = new ArrayList<>();
        this.amount = 1;
        this.glowing = false;
    }

    public ItemBuilder(Material type, String name, Integer amount) {
        this.type = type;
        this.name = name;
        this.lore = new ArrayList<>();
        this.amount = amount;
        this.glowing = false;
    }

    public ItemBuilder(Material type, String name, Integer amount, Boolean glowing) {
        this.type = type;
        this.name = name;
        this.lore = new ArrayList<>();
        this.amount = amount;
        this.glowing = glowing;
    }

    public ItemBuilder(Material type, String name, Integer amount, Boolean glowing, String... lore) {
        this.type = type;
        this.name = name;
        this.lore.addAll(Arrays.asList(lore));
        this.amount = amount;
        this.glowing = glowing;
    }


    public ItemStack getItem() {
        ItemStack item = new ItemStack(type);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if (glowing) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return item;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setType(Material type) {
        this.type = type;
        return this;
    }

    public ItemBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setLore(String ... lore) {
        List<String> lorelist = new ArrayList<>();
        lorelist.addAll(Arrays.asList(lore));
        this.lore = lorelist;
        return this;
    }

    public ItemBuilder setGlowing(Boolean glowing) {
        this.glowing = glowing;
        return this;
    }






}
