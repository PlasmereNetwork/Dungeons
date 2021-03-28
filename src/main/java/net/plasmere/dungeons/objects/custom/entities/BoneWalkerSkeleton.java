package net.plasmere.dungeons.objects.custom.entities;

import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class BoneWalkerSkeleton {
    public ItemStack weapon;

    public Skeleton entity;

    public BoneWalkerSkeleton(World world, Location location){
        this(world, location, CustomEntities.BONE_WALKER.defaultWeapon.key, CustomEntities.BONE_WALKER.defaultWeapon.value);
    }

    public BoneWalkerSkeleton(World world, Location location, ItemStack weapon, float chance){
        entity = world.spawn(location, Skeleton.class);
        entity.setCustomName(CustomEntities.BONE_WALKER.name);
        entity.setCustomNameVisible(true);

        entity.getEquipment().setItemInHand(weapon);
        entity.getEquipment().setItemInHandDropChance(chance);

        entity.getEquipment().setHelmet(CustomEntities.BONE_WALKER.head.key);
        entity.getEquipment().setHelmetDropChance(CustomEntities.BONE_WALKER.head.value);
        entity.getEquipment().setChestplate(CustomEntities.BONE_WALKER.chest.key);
        entity.getEquipment().setChestplateDropChance(CustomEntities.BONE_WALKER.chest.value);
        entity.getEquipment().setLeggings(CustomEntities.BONE_WALKER.legs.key);
        entity.getEquipment().setLeggingsDropChance(CustomEntities.BONE_WALKER.legs.value);
        entity.getEquipment().setBoots(CustomEntities.BONE_WALKER.boots.key);
        entity.getEquipment().setBootsDropChance(CustomEntities.BONE_WALKER.boots.value);

        entity.setMaxHealth(CustomEntities.BONE_WALKER.health);
        entity.setHealth(CustomEntities.BONE_WALKER.health);

        this.weapon = weapon;

        CustomEntities.addEntity(this.entity);
    }
}
