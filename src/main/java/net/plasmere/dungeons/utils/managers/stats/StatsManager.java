package net.plasmere.dungeons.utils.managers.stats;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.TreeSet;
import java.util.UUID;

public class StatsManager {
    public static String folder = Dungeons.getInstance().getDataFolder() + File.separator + "players" + File.separator;
    public static TreeSet<Stat> stats = new TreeSet<>();

    public static Cache<String, UUID> cachedUUIDs = Caffeine.newBuilder().build();
    public static Cache<UUID, String> cachedNames = Caffeine.newBuilder().build();

    public static void setup(){
        File file = new File(folder);
        if (file.exists()) return;
        if (! file.isDirectory()) return;

        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasStat(String latestName){
        return getStat(latestName) != null;
    }

    public static Stat getStat(Player player) {
        return getStat(player.getName());
    }

    public static Stat getStat(String username) {
        try {
            for (Stat stat : stats) {
                if (stat.latest_name.equals(username)) return stat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Stat getStat(UUID uuid) {
        try {
            for (Stat stat : stats) {
                if (stat.uuid.equals(uuid)) return stat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean exists(String username){
        File file = new File(folder, getCachedUUID(username) + ".properties");

        return file.exists();
    }

    public static UUID getCachedUUID(String username) {
        try {
            String finalUsername = username.replace("\"", "");
            return cachedUUIDs.get(username, (u) -> fetch(finalUsername));
        } catch (Exception e) {
            e.printStackTrace();
            return UUID.randomUUID();
        }
    }

    public static String getCachedName(UUID uuid) {
        try {
            return Objects.requireNonNull(cachedNames.get(uuid, (u) -> getName(uuid.toString()))).replace("\"", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    static public UUID fetch(String username) {
        username = username.toLowerCase(Locale.ROOT);
        try {
            String JSONString = "";

            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openConnection();
            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                JSONString = line;
            }

            JsonElement obj = new JsonParser().parse(JSONString);

            JsonObject jo = (JsonObject) obj;

            String id = jo.get("id").getAsString();

            String uuid = formatToUUID(id);

            return UUID.fromString(uuid);
            //return UUID.fromString(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return UUID.randomUUID();
    }

    public static String getName(String uuid) {
        try {
            String JSONString = "";

            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names").openConnection();
            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                JSONString = line;
            }

            Object obj = new JsonParser().parse(JSONString);
            JsonArray jo = (JsonArray) obj;
            String last = jo.get(jo.size() - 1).toString();
            Object job = new JsonParser().parse(last);
            JsonObject njo = (JsonObject) job;

            return njo.get("name").toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    public static String formatToUUID(String unformatted){
        StringBuilder formatted = new StringBuilder();
        int i = 1;
        for (Character character : unformatted.toCharArray()){
            if (i == 9 || i == 13 || i == 17 || i == 21){
                formatted.append("-").append(character);
            } else {
                formatted.append(character);
            }
            i++;
        }

        return formatted.toString();
    }

    public static Player getPlayer(UUID uuid){
        try {
            return Dungeons.getInstance().getServer().getPlayer(uuid);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Stat getSt(Player player) {
        try {
            String name = getCachedName(player.getUniqueId());

            if (exists(name)) {
                if (hasStat(name)) {
                    return getStat(name);
                } else {
                    return new Stat(name);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Stat getSt(UUID uuid){
        try {
            String name = getCachedName(uuid);

            if (exists(name)) {
                if (hasStat(name)) {
                    return getStat(name);
                } else {
                    return new Stat(name);
                }
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Stat getSt(String name){
        try {
            if (exists(name)) {
                if (hasStat(name)) {
                    return getStat(name);
                } else {
                    return new Stat(name);
                }
            }

            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String swapUUID(String uuid){
        if (uuid.contains("-")){
            return uuid.replace("-", "");
        } else {
            return formatToUUID(uuid);
        }
    }

    public static void onKill(Player killer, CustomEntities entity){
        Stat stat = getStat(killer);

        stat.addExpToSlaying((stat.getStatBonus(Stat.Bonuses.SLAYING) + 0 + 1) * entity.multiplier);
    }
}
