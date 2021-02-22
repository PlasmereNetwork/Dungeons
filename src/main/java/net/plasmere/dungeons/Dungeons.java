package net.plasmere.dungeons;

import net.plasmere.dungeons.config.ConfigHandler;
import net.plasmere.dungeons.config.ConfigUtils;
import net.plasmere.dungeons.utils.SQLUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dungeons extends JavaPlugin {
    private ConfigHandler configHandler;

    public ConfigHandler getConfigHandler(){
        return configHandler;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
