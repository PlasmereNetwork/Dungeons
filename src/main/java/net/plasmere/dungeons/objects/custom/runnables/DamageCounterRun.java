package net.plasmere.dungeons.objects.custom.runnables;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.objects.custom.entities.DamageStand;
import org.bukkit.scheduler.BukkitRunnable;

public class DamageCounterRun extends BukkitRunnable {
    public int countdown;
    public int reset;
    public boolean resetable;
    public DamageStand stand;

    public DamageCounterRun(int seconds, boolean resetable, DamageStand stand){
        this.countdown = seconds;
        this.reset = seconds;
        this.resetable = resetable;
        this.stand = stand;
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

        stand.entity.remove();
    }
}
