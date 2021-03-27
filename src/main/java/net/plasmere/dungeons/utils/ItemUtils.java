package net.plasmere.dungeons.utils;

import net.plasmere.dungeons.objects.SingleSet;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

public class ItemUtils {
    public static TreeSet<ItemStack> badStacks = new TreeSet<>(Collections.emptyList());

    public static ItemStack enachantedItem(ItemStack itemStack, Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);

        return itemStack;
    }

    public static SingleSet<ItemStack, Float> setDropChance(ItemStack itemStack, float percent){
        return new SingleSet<>(itemStack, percent);
    }

    public static boolean forGiveItemStack(Player player, Collection<ItemStack> itemStacks) {
        boolean trying = true;
        badStacks = new TreeSet<>(Collections.emptyList());
        for (ItemStack stack : itemStacks) {
            if (! giveItemStack(player, stack)) {
                trying = false;
                badStacks.add(stack);
            }
        }

        return trying;
    }

    public static boolean giveItemStack(Player player, ItemStack itemStack){
        if (player.getInventory().firstEmpty() == -1) return false;

        player.getInventory().addItem(itemStack);
        return true;
    }
}
