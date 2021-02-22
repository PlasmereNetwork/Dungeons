package net.plasmere.dungeons.config;

import net.plasmere.dungeons.Dungeons;
import org.bukkit.configuration.Configuration;

public class MessageConfUtils {
    private static Configuration mess = Dungeons.getInstance().getMess();

    public static void updateMess(Configuration m){
        mess = m;
    }

    public static String version = mess.getString("version");
    public static String join = mess.getString("join");
    public static String leave = mess.getString("leave");
}
