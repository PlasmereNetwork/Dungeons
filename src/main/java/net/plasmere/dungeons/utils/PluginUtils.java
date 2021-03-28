package net.plasmere.dungeons.utils;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.commands.*;
import net.plasmere.dungeons.listeners.PlayerListener;
import org.bukkit.Bukkit;

public class PluginUtils {
    public static void registerCommands() {
        Dungeons.getInstance().getCommand("test").setExecutor(new TestSpawnCommand());
        Dungeons.getInstance().getCommand("addenchantments").setExecutor(new AddEnchCommand());
        Dungeons.getInstance().getCommand("saveschem").setExecutor(new SchemSaveCommand());
        Dungeons.getInstance().getCommand("setspawning").setExecutor(new SetSpawningCommand());
        Dungeons.getInstance().getCommand("spawnatstands").setExecutor(new SpawnAtStandsCommand());
        Dungeons.getInstance().getCommand("getme").setExecutor(new GetMeCommand());
        Dungeons.getInstance().getCommand("setstats").setExecutor(new SetStatsCommand());
    }

    public static void registerEvents(){
        Dungeons.getInstance().pm.registerEvents(new PlayerListener(), Dungeons.getInstance());
    }

//    public static WorldEditPlugin getWorldEdit(){
//        return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
//    }
}
