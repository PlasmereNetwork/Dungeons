package net.plasmere.dungeons.config;

import net.plasmere.dungeons.Dungeons;
import org.bukkit.configuration.Configuration;

public class ConfigUtils {
    private static Configuration config = Dungeons.getInstance().getConfigHandler().getConf();

    public static void updateConfig(Configuration conf){
        config = conf;
    }

    public static String version = config.getString("version");
    public static String sqlHost = config.getString("mysql.host");
    public static int sqlPort = config.getInt("mysql.port");
    public static String sqlDatabase = config.getString("mysql.database");
    public static String sqlUsername = config.getString("mysql.username");
    public static String sqlPassword = config.getString("mysql.password");
}
