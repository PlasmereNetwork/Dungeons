package net.plasmere.dungeons.config;

import net.plasmere.dungeons.Dungeons;
import org.bukkit.configuration.Configuration;

public class ConfigUtils {
    private static Configuration config = Dungeons.getInstance().getConf();

    public static void updateConfig(Configuration conf){
        config = conf;
    }

    public static String version = config.getString("version");
}
