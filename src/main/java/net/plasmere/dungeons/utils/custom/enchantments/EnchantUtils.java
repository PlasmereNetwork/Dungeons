package net.plasmere.dungeons.utils.custom.enchantments;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.utils.managers.CustomEnchants;
import net.plasmere.dungeons.utils.TextUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnchantUtils {
    public static boolean updateCustomEnchants(ItemStack item){
        try {
            if (item.getEnchantments() == null) return false;
            if (item.getEnchantments().size() <= 0) return false;
        } catch (Exception e) {
            return false;
        }

        Map<Enchantment, Integer> enchMap = item.getEnchantments();

        if (item.getItemMeta().hasEnchant(CustomEnchants.GRABBING_HANDS)) {
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>();

            if (itemMeta.hasLore()) {
                for (String l : item.getItemMeta().getLore()) {
                    if (l.contains(CustomEnchants.GRABBING_HANDS.getName())) return false;
                }
                lore.addAll(itemMeta.getLore());
            }
            lore.add(TextUtils.codedString(
                    "&7" + CustomEnchants.GRABBING_HANDS.getName() + " " + TextUtils.integerToRoman(enchMap.get(CustomEnchants.GRABBING_HANDS)))
            );
            Dungeons.getInstance().getLogger().info("Logging enchant level: " + TextUtils.integerToRoman(enchMap.get(CustomEnchants.GRABBING_HANDS)));

            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
        }

        return true;
    }

    public static void forUpdateEnch(Player player){
        for (ItemStack stack : player.getInventory()){
            updateCustomEnchants(stack);
        }
    }
}
