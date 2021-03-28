package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.MessagingHandler;
import net.plasmere.dungeons.utils.TextUtils;
import net.plasmere.dungeons.utils.managers.stats.Stat;
import net.plasmere.dungeons.utils.managers.stats.StatsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.onlyPlayers);
            return false;
        }

        String p = sender.getName();

        if (args != null) {
            if (args.length == 1) {
                if (!sender.hasPermission("dungeons.admin")) {
                    MessagingHandler.sendSelfMessage(sender, MessageConfUtils.noPerm);
                    return false;
                }

                p = args[0];
            }
        }

        if (! StatsManager.exists(p)) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.noPlayer);
            return false;
        }

        Stat stat = StatsManager.getOrCreate(p);

        MessagingHandler.sendSelfMessage(stat.player, MessageConfUtils.cGetMe
                .replace("%player%", stat.player.getDisplayName())
                .replace("%seconds%", String.valueOf(stat.play_seconds))
                .replace("%hours%", TextUtils.truncate(String.valueOf(stat.getPlayHours()), 3))
                .replace("%slaying_exp%", String.valueOf(stat.slaying_exp))
                .replace("%slaying_lvl%", String.valueOf(stat.slaying_lvl))
                .replace("%forging_exp%", String.valueOf(stat.forging_exp))
                .replace("%forging_lvl%", String.valueOf(stat.forging_lvl))
        );

        return true;
    }
}
