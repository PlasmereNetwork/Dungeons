package net.plasmere.dungeons.utils.managers.stats;

import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.entity.Player;

public class StatisticsHandler {
    public static double returnDamage(double oldDamage, Player player){
        Stat stat = StatsManager.getOrCreate(player);

        double newDamage = (oldDamage * stat.slaying_lvl + stat.slaying_lvl * 100) + oldDamage;

        if (newDamage <= 0) return oldDamage;

        return newDamage;
    }

    public static void onKill(Player killer, CustomEntities entity){
        Stat stat = StatsManager.getOrCreate(killer);

        stat.addExpToSlaying((stat.getStatBonus(Stat.Bonuses.SLAYING) + 0 + 1) * entity.multiplier);
    }
}
