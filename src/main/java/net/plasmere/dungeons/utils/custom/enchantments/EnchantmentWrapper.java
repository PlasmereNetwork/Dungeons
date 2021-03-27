package net.plasmere.dungeons.utils.custom.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class EnchantmentWrapper extends Enchantment {
    private final String name;
    private final int maxLvl;

    public EnchantmentWrapper(int id, String name, int maxLvl) {
        super(id);

        this.name = name;
        this.maxLvl = maxLvl;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLvl;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }
}
