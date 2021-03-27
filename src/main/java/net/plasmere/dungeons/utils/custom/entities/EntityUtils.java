package net.plasmere.dungeons.utils.custom.entities;

import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    public static Class<CustomEntities> customEntities = CustomEntities.class;

    public static List<String> getCustomEntities() {
        List<String> entities = new ArrayList<>();

        for (CustomEntities entity : CustomEntities.values()) {
            entities.add(entity.var);
        }

        return entities;
    }

    public static boolean hasPotionType(Player player, PotionEffectType type){
        for (PotionEffect effect : player.getActivePotionEffects()){
            if (effect.getType().equals(type)) return true;
        }

        return false;
    }

    public static boolean isCrit(Player player){
        return player.getFallDistance() > 0.0F
                && ! player.isOnGround()
                && ! player.getLocation().getBlock().isLiquid()
                && ! hasPotionType(player, PotionEffectType.BLINDNESS)
                && ! player.isInsideVehicle()
                && ! player.isSprinting();
    }
}
