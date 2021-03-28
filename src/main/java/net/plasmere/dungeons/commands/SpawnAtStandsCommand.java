package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.MessagingHandler;
import net.plasmere.dungeons.utils.custom.entities.EntityUtils;
import net.plasmere.dungeons.utils.managers.CustomEntities;
import net.plasmere.dungeons.utils.managers.worlds.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnAtStandsCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.onlyPlayers);
            return false;
        }

        if (args == null) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.needsMore);
            return true;
        }

        if (args.length <= 0) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.needsMore);
            return true;
        }

        if (args[0].equals(CustomEntities.BONE_WALKER.var)) {
            WorldManager.summonCreatures(CustomEntities.BONE_WALKER, 2);
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cStandSpawning
                    .replace("%entity%", CustomEntities.BONE_WALKER.name)
                    .replace("%chance%", String.valueOf(2))
            );
        } else if (args[0].equals(CustomEntities.VENOMOUS_TARANTULA.var)) {
            WorldManager.summonCreatures(CustomEntities.VENOMOUS_TARANTULA, 2);
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cStandSpawning
                    .replace("%entity%", CustomEntities.VENOMOUS_TARANTULA.name)
                    .replace("%chance%", String.valueOf(2))
            );
        } else {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.error);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return EntityUtils.getCustomEntities();
    }
}
