package net.plasmere.dungeons.listeners;

import net.plasmere.dungeons.utils.managers.CustomEnchants;
import net.plasmere.dungeons.objects.custom.entities.DamageStand;
import net.plasmere.dungeons.utils.ItemUtils;
import net.plasmere.dungeons.utils.TextUtils;
import net.plasmere.dungeons.utils.custom.enchantments.EnchantUtils;
import net.plasmere.dungeons.utils.custom.entities.EntityUtils;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import net.plasmere.dungeons.utils.managers.stats.StatsManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlayerListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        for (ItemStack stack : player.getInventory()){
            EnchantUtils.updateCustomEnchants(stack);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event){
        //Dungeons.getInstance().getLogger().info("Block breaking...");
        if (event.getPlayer().getInventory().getItemInHand() == null) return;
        if (! event.getPlayer().getInventory().getItemInHand().hasItemMeta()) return;
        if (! event.getPlayer().getInventory().getItemInHand().getItemMeta().hasEnchant(CustomEnchants.GRABBING_HANDS)) return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (event.getPlayer().getInventory().firstEmpty() == -1) return;
        if (event.getBlock().getState() instanceof InventoryHolder) return;

        //Dungeons.getInstance().getLogger().info("Block breaking... true");

        Player player = event.getPlayer();
        Collection<ItemStack> drops = event.getBlock().getDrops(player.getItemInHand());

        if (drops.isEmpty()) return;
        player.getInventory().addItem(drops.iterator().next());

        event.getBlock().setType(Material.AIR);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKillEntity(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (player == null) return;

        if (player.getItemInHand() == null) return;
        if (! player.getItemInHand().hasItemMeta()) return;
        if (! player.getItemInHand().getItemMeta().hasEnchant(CustomEnchants.GRABBING_HANDS)) return;

        Collection<ItemStack> drops = event.getDrops();

        event.getEntity().getEquipment().setItemInHandDropChance(0);
        event.getEntity().getEquipment().setHelmetDropChance(0);
        event.getEntity().getEquipment().setChestplateDropChance(0);
        event.getEntity().getEquipment().setLeggingsDropChance(0);
        event.getEntity().getEquipment().setBootsDropChance(0);

        if (! ItemUtils.forGiveItemStack(player, drops)) {
            for (ItemStack stack : ItemUtils.badStacks) {
                Item item = event.getEntity().getWorld().spawn(event.getEntity().getLocation(), Item.class);
                item.setItemStack(stack);
            }
        }

        event.getDrops().clear();

        CustomEntities ce = CustomEntities.getEntity(event.getEntity().getCustomName());
        if (! ce.equals(CustomEntities.NULL)) {
            StatsManager.onKill(player, ce);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        Player player = (Player) event.getDamager();

        if (event.getEntity() instanceof ArmorStand) return;

        new DamageStand(entity.getWorld(), entity.getLocation(), event.getDamage(), EntityUtils.isCrit(player));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnchantedClick(InventoryClickEvent event){
        Inventory inventory = event.getInventory();

        //Dungeons.getInstance().getLogger().info("Detected event... open");

        for (ItemStack itemStack : inventory.getContents()) {
            EnchantUtils.updateCustomEnchants(itemStack);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPickUp(PlayerPickupItemEvent event){
        Inventory inventory = event.getPlayer().getInventory();

        //Dungeons.getInstance().getLogger().info("Detected event... open");

        for (ItemStack itemStack : inventory.getContents()) {
            EnchantUtils.updateCustomEnchants(itemStack);
        }

        ItemStack item = event.getItem().getItemStack();

        EnchantUtils.updateCustomEnchants(item);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnchantedItem(EnchantItemEvent event){
        Map<Enchantment, Integer> enchMap = event.getEnchantsToAdd();
        if (enchMap.containsKey(CustomEnchants.GRABBING_HANDS)) {
            ItemStack itemStack = event.getItem();
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> lore = new ArrayList<>();

            if (itemMeta.hasLore()) {
                lore.addAll(itemMeta.getLore());
            }
            lore.add(TextUtils.codedString(
                    "&7" + CustomEnchants.GRABBING_HANDS.getName() + " " + TextUtils.integerToRoman(enchMap.get(CustomEnchants.GRABBING_HANDS)))
            );
//            Dungeons.getInstance().getLogger().info("Logging enchant level: " + TextUtils.integerToRoman(enchMap.get(CustomEnchants.GRABBING_HANDS)));

            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
    }
}
