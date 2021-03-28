package net.plasmere.dungeons.objects.custom.entities;

import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CaveSpider;
import org.bukkit.inventory.ItemStack;

public class VenomousTerrantulaSpider {
    public ItemStack weapon;

    public CaveSpider entity;

    public VenomousTerrantulaSpider(World world, Location location){
        this(world, location, CustomEntities.VENOMOUS_TARANTULA.defaultWeapon.key, CustomEntities.VENOMOUS_TARANTULA.defaultWeapon.value);
    }

    public VenomousTerrantulaSpider(World world, Location location, ItemStack weapon, float chance){
        entity = world.spawn(location, CaveSpider.class);
        entity.setCustomName(CustomEntities.VENOMOUS_TARANTULA.name);
        entity.setCustomNameVisible(true);

        entity.getEquipment().setItemInHand(weapon);
        entity.getEquipment().setItemInHandDropChance(chance);

        entity.getEquipment().setHelmet(CustomEntities.VENOMOUS_TARANTULA.head.key);
        entity.getEquipment().setHelmetDropChance(CustomEntities.VENOMOUS_TARANTULA.head.value);
        entity.getEquipment().setChestplate(CustomEntities.VENOMOUS_TARANTULA.chest.key);
        entity.getEquipment().setChestplateDropChance(CustomEntities.VENOMOUS_TARANTULA.chest.value);
        entity.getEquipment().setLeggings(CustomEntities.VENOMOUS_TARANTULA.legs.key);
        entity.getEquipment().setLeggingsDropChance(CustomEntities.VENOMOUS_TARANTULA.legs.value);
        entity.getEquipment().setBoots(CustomEntities.VENOMOUS_TARANTULA.boots.key);
        entity.getEquipment().setBootsDropChance(CustomEntities.VENOMOUS_TARANTULA.boots.value);

        entity.setMaxHealth(CustomEntities.VENOMOUS_TARANTULA.health);
        entity.setHealth(CustomEntities.VENOMOUS_TARANTULA.health);

        this.weapon = weapon;

        CustomEntities.addEntity(this.entity);
    }
}
