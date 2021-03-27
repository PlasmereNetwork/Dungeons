package net.plasmere.dungeons.objects.custom.runnables;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.utils.custom.enchantments.EnchantUtils;
import net.plasmere.dungeons.utils.managers.worlds.WorldManager;
import org.bukkit.entity.Player;
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
        }

        WorldManager.checkAndDelete();
    }
}