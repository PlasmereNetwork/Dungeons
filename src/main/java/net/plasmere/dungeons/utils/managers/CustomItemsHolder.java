package net.plasmere.dungeons.utils.managers;

import java.util.TreeMap;

public class CustomItemsHolder {
    public static TreeMap<Integer, Integer> plotArmor(){
        TreeMap<Integer, Integer> t = new TreeMap<>();
        t.put(0, 10);
        t.put(7, 10);

        return t;
    }
}
