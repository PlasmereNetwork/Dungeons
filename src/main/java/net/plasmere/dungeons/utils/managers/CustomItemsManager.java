package net.plasmere.dungeons.utils.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.TreeMap;

public enum CustomItemsManager {
    PLOT_ARMOR_HELMET(new ItemStack(Material.DIAMOND_HELMET), CustomItemsHolder.plotArmor()),
    PLOT_ARMOR_CHEST(new ItemStack(Material.DIAMOND_CHESTPLATE), CustomItemsHolder.plotArmor()),
    PLOT_ARMOR_LEGS(new ItemStack(Material.DIAMOND_LEGGINGS), CustomItemsHolder.plotArmor()),
    PLOT_ARMOR_BOOTS(new ItemStack(Material.DIAMOND_BOOTS), CustomItemsHolder.plotArmor());

    public ItemStack item;
    public TreeMap<Integer, Integer> enchs;

    private CustomItemsManager(ItemStack item, TreeMap<Integer, Integer> enchs){
        this.item = item;
        this.enchs = enchs;
    }
}
