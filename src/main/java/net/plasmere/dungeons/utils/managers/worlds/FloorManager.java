package net.plasmere.dungeons.utils.managers.worlds;

import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;
import java.util.TreeSet;

public class FloorManager {
    public static void build(Floors floors, World world){
//        Random RNG = new Random();
//
//        File[] files = WorldManager.floorsSchemPath.listFiles();
//        if (files == null) return;
//
//        TreeSet<File> floorSchems = new TreeSet<>();
//
//        for (File file : files) {
//            if (file.getName().startsWith(floors.namePre)) floorSchems.add(file);
//        }
//
//        File[] fs = floorSchems.toArray(new File[0]);
//
//        int tile = 0;
//        while (tile <= floors.tiles) {
//            int file = RNG.nextInt(floorSchems.size());
//
//            try {
//                ClipboardFormat format = ClipboardFormat.findByFile(fs[file]);
//                ClipboardReader reader = format.getReader(new FileInputStream(fs[file]));
//                Clipboard clipboard = reader.read(WorldManager.getWorldData());
//
//                EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession((LocalWorld) world, -1);
//                Operation operation = new ClipboardHolder(clipboard, WorldManager.getWorldData())
//                        .createPaste(editSession, WorldManager.getWorldData())
//                        .to(new Vector())
//                        .ignoreAirBlocks(false)
//                        .build();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            tile ++;
//        }
    }

    public static Floors getFloorByString(String floor){
        for (Floors floors : Floors.values()){
            if (floor.startsWith(floors.namePre)) return floors;
        }

        return Floors.FLOOR_ONE;
    }
}
