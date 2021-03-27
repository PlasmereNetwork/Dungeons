package net.plasmere.dungeons.objects.custom.runnables;

import net.plasmere.dungeons.utils.managers.CustomEntities;
import net.plasmere.dungeons.utils.managers.worlds.WorldManager;
import org.bukkit.scheduler.BukkitRunnable;

public class SummonRun extends BukkitRunnable {
    public int countdown;
    public int reset;
    public boolean resetable;
    public CustomEntities entity;
    public int chance;

    public SummonRun(int seconds, boolean resetable, CustomEntities entity, int outOf){
        this.countdown = seconds;
        this.reset = seconds;
        this.resetable = resetable;
        this.entity = entity;
        this.chance = outOf;
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

        WorldManager.summonCreatures(entity, chance);
    }
}
