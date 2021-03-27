package net.plasmere.dungeons;

import net.plasmere.dungeons.config.ConfigHandler;
import net.plasmere.dungeons.objects.custom.runnables.SummonRun;
import net.plasmere.dungeons.utils.TextUtils;
import net.plasmere.dungeons.utils.managers.CustomEnchants;
import net.plasmere.dungeons.objects.custom.runnables.SecondsTimerRun;
import net.plasmere.dungeons.utils.PluginUtils;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import net.plasmere.dungeons.utils.managers.worlds.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Dungeons extends JavaPlugin {
    private ConfigHandler configHandler;

    public PluginManager pm;
    public ConfigHandler getConfigHandler(){
        return configHandler;
    }
    public BukkitRunnable secondsTimer;
    public BukkitRunnable VSSummon;
    public BukkitRunnable BWSummon;
    public static ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        pm = this.getServer().getPluginManager();

        if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
            console.sendMessage(TextUtils.codedString("&cWorldEdit not loaded!"));
            getServer().getPluginManager().disablePlugin(this);
        }

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        secondsTimer.cancel();
        BWSummon.cancel();
        VSSummon.cancel();
    }
}
