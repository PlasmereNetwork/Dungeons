package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.MessagingHandler;
import net.plasmere.dungeons.utils.managers.CustomItemsManager;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GetItemCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.noPlayer);
            return false;
        }

        Player player = (Player) sender;

        if (args[0].equals("plot_armor")) {
            ItemStack helm = CustomItemsManager.PLOT_ARMOR_HELMET.item;
            for (int id : CustomItemsManager.PLOT_ARMOR_HELMET.enchs.keySet()) {
                helm.addUnsafeEnchantment(new EnchantmentWrapper(id), CustomItemsManager.PLOT_ARMOR_HELMET.enchs.get(id));
            }

            ItemStack chest = CustomItemsManager.PLOT_ARMOR_CHEST.item;
            for (int id : CustomItemsManager.PLOT_ARMOR_CHEST.enchs.keySet()) {
                chest.addUnsafeEnchantment(new EnchantmentWrapper(id), CustomItemsManager.PLOT_ARMOR_CHEST.enchs.get(id));
            }

            ItemStack legs = CustomItemsManager.PLOT_ARMOR_LEGS.item;
            for (int id : CustomItemsManager.PLOT_ARMOR_LEGS.enchs.keySet()) {
                legs.addUnsafeEnchantment(new EnchantmentWrapper(id), CustomItemsManager.PLOT_ARMOR_LEGS.enchs.get(id));
            }

            ItemStack boots = CustomItemsManager.PLOT_ARMOR_BOOTS.item;
            for (int id : CustomItemsManager.PLOT_ARMOR_BOOTS.enchs.keySet()) {
                boots.addUnsafeEnchantment(new EnchantmentWrapper(id), CustomItemsManager.PLOT_ARMOR_BOOTS.enchs.get(id));
            }

            if (player.getInventory().getHelmet().equals(new ItemStack(Material.AIR))) {
                player.getInventory().setHelmet(helm);
            } else {
                if (player.getInventory().firstEmpty() == -1) {
                    Item item = player.getWorld().spawn(player.getLocation(), Item.class);
                    item.setItemStack(helm);
                }

                player.getInventory().addItem(helm);
            }

            if (player.getInventory().getChestplate().equals(new ItemStack(Material.AIR))) {
                player.getInventory().setChestplate(chest);
            } else {
                if (player.getInventory().firstEmpty() == -1) {
                    Item item = player.getWorld().spawn(player.getLocation(), Item.class);
                    item.setItemStack(chest);
                }

                player.getInventory().addItem(chest);
            }

            if (player.getInventory().getLeggings().equals(new ItemStack(Material.AIR))) {
                player.getInventory().setLeggings(legs);
            } else {
                if (player.getInventory().firstEmpty() == -1) {
                    Item item = player.getWorld().spawn(player.getLocation(), Item.class);
                    item.setItemStack(legs);
                }

                player.getInventory().addItem(legs);
            }

            if (player.getInventory().getBoots().equals(new ItemStack(Material.AIR))) {
                player.getInventory().setBoots(boots);
            } else {
                if (player.getInventory().firstEmpty() == -1) {
                    Item item = player.getWorld().spawn(player.getLocation(), Item.class);
                    item.setItemStack(boots);
                }

                player.getInventory().addItem(boots);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> items = new ArrayList<>();

        items.add("plot_armor");

        return items;
    }
}
