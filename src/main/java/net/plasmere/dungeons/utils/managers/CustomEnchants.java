package net.plasmere.dungeons.utils.managers;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.utils.custom.enchantments.EnchantmentWrapper;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CustomEnchants {
    public static final Enchantment GRABBING_HANDS = new EnchantmentWrapper(500, "Grabbing Hands", 1);

    public static void checkRegister(Enchantment enchantment){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);

        if (! registered) registerEnchantment(enchantment);
    }

    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);

            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }

        if (registered) {
            Dungeons.getInstance().getLogger().info("Registered enchantment: " + enchantment.getName());
        }
    }

    public static List<String> getEnchs(){
        List<String> strings = new ArrayList<>();

        strings.add(GRABBING_HANDS.getName().replace(" ", "_").toLowerCase(Locale.ROOT));

        return strings;
    }
}
