package net.plasmere.dungeons.objects.custom.entities;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.objects.custom.runnables.DamageCounterRun;
import net.plasmere.dungeons.utils.TextUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class DamageStand {
    public ArmorStand entity;

    public DamageStand(World world, Location location, double damage, boolean crit){
        entity = world.spawn(location.add(0, -1, 0), ArmorStand.class);

        entity.setVisible(false);
        entity.setGravity(false);
        if (crit) {
            entity.setCustomName(TextUtils.codedString("&6" + damage));
        } else {
            entity.setCustomName(TextUtils.codedString("&7" + damage));
        }

        entity.setCustomNameVisible(true);
        entity.setSmall(true);

        new DamageCounterRun(2, false, this).runTaskTimerAsynchronously(Dungeons.getInstance(), 1, 20);
    }
}
