package net.plasmere.dungeons.handlers;

import net.plasmere.dungeons.Dungeons;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections8.Reflections;

import java.util.Set;

public class ListenerHandler {

    public ListenerHandler() {
        Reflections reflections = new Reflections("net.plasmere.dungeons.listeners");
        Set<Class<? extends Listener>> listeners = reflections.getSubTypesOf(Listener.class);

        for (Class<? extends Listener> listener : listeners) {
            try {
                Listener instance = listener.newInstance();
                Bukkit.getPluginManager().registerEvents(instance, Dungeons.getInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
