package net.plasmere.dungeons.utils.managers.worlds;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.world.registry.WorldData;
import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.objects.custom.entities.BoneWalkerSkeleton;
import net.plasmere.dungeons.objects.custom.entities.VenomousTerrantulaSpider;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.ArmorStand;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.*;

public class WorldManager {
    public static File floorsSchemPath = new File(Dungeons.getInstance().getDataFolder() + "saves" + File.separator);
    public static List<String> folders = new ArrayList<>(Arrays.asList("floor1", "floor2"));
    public static TreeMap<Floors, Clipboard> floorPieces = new TreeMap<>();

    public static boolean check(World world){
        return world.getPlayers().size() > 0;
    }

    public static void checkAndDelete(){
        for (World world : Dungeons.getInstance().getServer().getWorlds()){
            if (world.getName().equals("world") || world.getName().equals("the_end")) continue;
            if (check(world)) continue;

            Bukkit.unloadWorld(world, false);
            world.getWorldFolder().delete();
        }
    }

    public static void createFloor(Floors floor){
        WorldCreator creator = new WorldCreator(floor.namePre + getNextFloorWorldInt(floor));
        creator.type(WorldType.FLAT);
        creator.generatorSettings("minecraft:air;minecraft:air");
        creator.generateStructures(false);
        World newWorld = creator.createWorld();

        newWorld.setGameRuleValue("keepInventory", "true");

        FloorManager.build(floor, newWorld);
    }

    public static int getNextFloorWorldInt(Floors floorType){
        int number = 0;
        for (World world : Dungeons.getInstance().getServer().getWorlds()) {
            if (! world.getName().startsWith(floorType.namePre)) continue;
            if (! world.getName().equals(floorType.namePre + number)) continue;
            number ++;
        }

        return number;
    }

    public static void setUpSchemFolders(){
        for (String floor : folders) {
            try {
                File file = new File(floorsSchemPath + floor + File.separator);

                if (file.exists()) continue;

                file.createNewFile();
            } catch (Exception e) {
                Dungeons.getInstance().getLogger().info("Error setting up a folder...");
            }
        }
    }

    public static void getFloorPieces(){
        if (floorPieces != null) floorPieces.clear();

        File[] files = floorsSchemPath.listFiles();

        if (files == null) return;
        if (files.length <= 0) return;

        for (File file : files){
            ClipboardFormat format = ClipboardFormat.findByFile(file);

            if (format == null) continue;

            try {
                ClipboardReader reader = format.getReader(new FileInputStream(file));
                Clipboard clipboard = reader.read(getWorldData());

                floorPieces.put(FloorManager.getFloorByString(file.getName()), clipboard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static WorldData getWorldData() {
        try {
            Class<?> wd = Class.forName("com.sk89q.worldedit.bukkit.BukkitWorldData");
            Method methodInstance = wd.getDeclaredMethod("getInstance");
            methodInstance.setAccessible(true);
            return (WorldData) methodInstance.invoke(null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void summonCreatures(CustomEntities entity, int chance){
        Random RNG = new Random();

        if (entity.equals(CustomEntities.NULL)) {
            List<World> worlds = Dungeons.getInstance().getServer().getWorlds();

            for (World world : worlds){
                Collection<ArmorStand> stands = world.getEntitiesByClass(ArmorStand.class);

                TreeSet<ArmorStand> st = new TreeSet<>();

                for (ArmorStand stand : stands){
                    if (! stand.getCustomName().startsWith("spawn.")) continue;

                    st.add(stand);
                }

                for (ArmorStand stand : st) {
                    int r = RNG.nextInt(chance);

                    String eString = stand.getCustomName().substring("spawn.".length());

                    if (CustomEntities.is(eString, CustomEntities.BONE_WALKER)) {
                        if (r != 0) continue;

                        new BoneWalkerSkeleton(stand.getWorld(), stand.getLocation());
                    } else if (CustomEntities.is(eString, CustomEntities.VENOMOUS_TARANTULA)) {
                        if (r != 0) continue;

                        new VenomousTerrantulaSpider(stand.getWorld(), stand.getLocation());
                    }
                }
            }
        } else {
            List<World> worlds = Dungeons.getInstance().getServer().getWorlds();

            for (World world : worlds){
                Collection<ArmorStand> stands = world.getEntitiesByClass(ArmorStand.class);

                TreeSet<ArmorStand> st = new TreeSet<>();

                for (ArmorStand stand : stands){
                    if (! stand.getCustomName().startsWith("spawn.")) continue;

                    st.add(stand);
                }

                for (ArmorStand stand : st) {
                    int r = RNG.nextInt(chance);

                    String eString = stand.getCustomName().substring("spawn.".length());

                    if (CustomEntities.is(eString, entity)) {
                        if (r != 0) continue;

                        CustomEntities.summon(entity, stand.getLocation());
                    }
                }
            }
        }
    }
}
