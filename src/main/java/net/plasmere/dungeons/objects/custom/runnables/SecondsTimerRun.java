package net.plasmere.dungeons.objects.custom.runnables;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.utils.custom.enchantments.EnchantUtils;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import net.plasmere.dungeons.utils.managers.stats.Stat;
import net.plasmere.dungeons.utils.managers.stats.StatsManager;
import net.plasmere.dungeons.utils.managers.worlds.WorldManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class SecondsTimerRun extends BukkitRunnable {
    public int countdown;
    public int reset;
    public boolean resetable;

    public SecondsTimerRun(int seconds, boolean resetable){
        this.countdown = seconds;
        this.reset = seconds;
        this.resetable = resetable;
    }

    @Override
    public void run() {
        if (countdown <= 0) {
            done();

            if (! resetable) {
                this.cancel();
                return;
            }
        }

        countdown --;
    }

    public void done(){
        if (resetable) {
            countdown = reset;
        }

        for (Player player : Dungeons.getInstance().getServer().getOnlinePlayers()) {
            EnchantUtils.forUpdateEnch(player);

            for (ItemStack stack : player.getInventory().getContents()) {
                if (stack == null) continue;

                EnchantUtils.updateCustomEnchants(stack);

                if (stack.getItemMeta() == null) continue;

                ItemMeta meta = stack.getItemMeta();
                meta.spigot().setUnbreakable(true);
                stack.setItemMeta(meta);

                Stat stat = StatsManager.getOrCreate(player);

                stat.addPlaySeconds(1);
            }
        }

        try {
            WorldManager.checkAndDelete();

            CustomEntities.updateAndRemove();
        } catch (Exception e) {
            // do nothing.
        }
    }
}
