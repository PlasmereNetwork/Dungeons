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
    public static String noPlayer = mess.getString("no-player");
    public static String noPerm = mess.getString("no-perm");
    public static String onlyPlayers = mess.getString("only-players");
    public static String needsMore = mess.getString("needs-more");
    public static String needsLess = mess.getString("needs-less");

    // Players.
    public static String offline = mess.getString("players.offline");
    public static String online = mess.getString("players.online");

    // World Edit API:
    public static String weNoSelection = mess.getString("worldedit-api.no-selection");

    public static String cSetSpawning = mess.getString("commands.set-spawning");
    public static String cStandSpawning = mess.getString("commands.stand-spawning");
    public static String cGetMe = mess.getString("commands.get-me");
    public static String cSetStat = mess.getString("commands.set-stat");
}
