package net.plasmere.dungeons.utils;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.commands.AddEnchCommand;
import net.plasmere.dungeons.commands.SchemSaveCommand;
import net.plasmere.dungeons.commands.TestSpawnCommand;
import net.plasmere.dungeons.listeners.PlayerListener;
import org.bukkit.Bukkit;

public class PluginUtils {
    public static void registerCommands(){
        Dungeons.getInstance().getCommand("test").setExecutor(new TestSpawnCommand());
        Dungeons.getInstance().getCommand("addenchantments").setExecutor(new AddEnchCommand());
        Dungeons.getInstance().getCommand("saveschem").setExecutor(new SchemSaveCommand());
    }

    public static void registerEvents(){
        Dungeons.getInstance().pm.registerEvents(new PlayerListener(), Dungeons.getInstance());
    }

    public static WorldEditPlugin getWorldEdit(){
        return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }
}
