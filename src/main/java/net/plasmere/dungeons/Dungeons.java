package net.plasmere.dungeons;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dungeons extends JavaPlugin {

    private Configuration conf;
    private Configuration mess;

    public Configuration getConf() {
        return conf;
    }

    public Configuration getMess() {
        return mess;
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
