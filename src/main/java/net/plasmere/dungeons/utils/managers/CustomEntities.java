package net.plasmere.dungeons.utils.managers;

import net.plasmere.dungeons.objects.SingleSet;
import net.plasmere.dungeons.objects.custom.entities.BoneWalkerSkeleton;
import net.plasmere.dungeons.objects.custom.entities.VenomousTerrantulaSpider;
import net.plasmere.dungeons.utils.ItemUtils;
import net.plasmere.dungeons.utils.TextUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public enum CustomEntities {
    NULL("NULL",
            TextUtils.codedString("&c&lNULL"), 0,
            new SingleSet<>(new ItemStack(Material.AIR), 0f),
            new SingleSet<>(new ItemStack(Material.AIR), 0f),
            new SingleSet<>(new ItemStack(Material.AIR), 0f),
            new SingleSet<>(new ItemStack(Material.AIR), 0f),
            new SingleSet<>(new ItemStack(Material.AIR), 0f),
            0.0f
    ),
    BONE_WALKER("bone_walker",
            TextUtils.codedString("&6&lBone Walker"), 10,
            new SingleSet<>(ItemUtils.enachantedItem(new ItemStack(Material.BONE, 1), Enchantment.DAMAGE_ALL, 10), 0.1f),
            new SingleSet<>(ItemUtils.enachantedItem(new ItemStack(Material.DIAMOND_HELMET, 1), Enchantment.PROTECTION_ENVIRONMENTAL, 5), 0.1f),
            new SingleSet<>(ItemUtils.enachantedItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), Enchantment.PROTECTION_ENVIRONMENTAL, 5), 0.1f),
            new SingleSet<>(ItemUtils.enachantedItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1), Enchantment.PROTECTION_ENVIRONMENTAL, 5), 0.1f),
            new SingleSet<>(ItemUtils.enachantedItem(new ItemStack(Material.DIAMOND_BOOTS, 1), Enchantment.PROTECTION_ENVIRONMENTAL, 5), 0.1f),
            1.3f
    ),
    VENOMOUS_TARANTULA("venomous_tarantula",
            TextUtils.codedString("&c&lVenomous &6Terrantula"), 100,
            new SingleSet<>(new ItemStack(Material.AIR, 1), 0.1f),
            new SingleSet<>(new ItemStack(Material.AIR, 1), 0.1f),
            new SingleSet<>(new ItemStack(Material.AIR, 1), 0.1f),
            new SingleSet<>(new ItemStack(Material.AIR, 1), 0.1f),
            new SingleSet<>(new ItemStack(Material.AIR, 1), 0.1f),
            1.2f
    );

    public String var;
    public String name;
    public int health;
    public SingleSet<ItemStack, Float> defaultWeapon;
    public SingleSet<ItemStack, Float> head;
    public SingleSet<ItemStack, Float> chest;
    public SingleSet<ItemStack, Float> legs;
    public SingleSet<ItemStack, Float> boots;
    public float multiplier;

    CustomEntities(String var, String name, int health, SingleSet<ItemStack, Float> defaultWeapon,
                           SingleSet<ItemStack, Float> head, SingleSet<ItemStack, Float> chest,
                           SingleSet<ItemStack, Float> legs, SingleSet<ItemStack, Float> boots,
                   float multiplier){
        this.var = var;
        this.name = name;
        this.health = health;
        this.defaultWeapon = defaultWeapon;
        this.head = head;
        this.chest = chest;
        this.legs = legs;
        this.boots = boots;
        this.multiplier = multiplier;
    }

    public static CustomEntities getEntity(String name){
        if (name.equals(BONE_WALKER.name)) return BONE_WALKER;
        if (name.equals(VENOMOUS_TARANTULA.name)) return VENOMOUS_TARANTULA;
        return NULL;
    }

    public static boolean is(String name, CustomEntities entity){
        return name.equals(entity.name);
    }

    public static void summon(CustomEntities entity, Location location){
        if (entity.equals(BONE_WALKER)) {
            new BoneWalkerSkeleton(location.getWorld(), location);
        } else if (entity.equals(VENOMOUS_TARANTULA)) {
            new VenomousTerrantulaSpider(location.getWorld(), location);
        }
    }
}
