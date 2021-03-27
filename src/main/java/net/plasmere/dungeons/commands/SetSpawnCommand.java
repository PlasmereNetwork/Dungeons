package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.MessagingHandler;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetSpawnCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.onlyPlayers);
            return false;
        }

        Player player = ((Player) sender).getPlayer();

        if (args[0].equals(CustomEntities.BONE_WALKER.name)) {
            ArmorStand stand = player.getWorld().spawn(player.getLocation().getBlock().getLocation(), ArmorStand.class);
            stand.setSmall(true);
            stand.setCustomName("spawn." + CustomEntities.BONE_WALKER.name);
            stand.setGravity(false);
            stand.setVisible(false);
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cSetSpawning
                    .replace("%entity%", CustomEntities.BONE_WALKER.name)
                    .replace("%location%", player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ())
            );
        } else if (args[0].equals(CustomEntities.VENOMOUS_TARANTULA.name)) {
            ArmorStand stand = player.getWorld().spawn(player.getLocation().getBlock().getLocation(), ArmorStand.class);
            stand.setSmall(true);
            stand.setCustomName("spawn." + CustomEntities.VENOMOUS_TARANTULA.name);
            stand.setGravity(false);
            stand.setVisible(false);
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cSetSpawning
                    .replace("%entity%", CustomEntities.VENOMOUS_TARANTULA.name)
                    .replace("%location%", player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ())
            );
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
