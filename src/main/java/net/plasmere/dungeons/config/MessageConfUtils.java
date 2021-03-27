package net.plasmere.dungeons.config;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.backend.conf.Configuration;

public class MessageConfUtils {
    private static Configuration mess = Dungeons.getInstance().getConfigHandler().getMess();

    public static void updateMess(Configuration m){
        mess = m;
    }

    public static String version = mess.getString("version");
    public static String join = mess.getString("join");
    public static String leave = mess.getString("leave");
    public static String error = mess.getString("error");
    public static String onlyPlayers = mess.getString("only-players");
    public static String needsMore = mess.getString("needs-more");
    public static String needsLess = mess.getString("needs-less");

    // World Edit API:
    public static String weNoSelection = mess.getString("worldedit-api.no-selection");

    public static String cSetSpawning = mess.getString("commands.set-spawning");
}
