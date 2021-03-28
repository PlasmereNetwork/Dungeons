package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.MessagingHandler;
import net.plasmere.dungeons.utils.managers.stats.Stat;
import net.plasmere.dungeons.utils.managers.stats.StatsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetStatsCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.needsMore);
            return false;
        }

        if (args.length < 1) {
            MessagingHandler.sendSelfMessage(sender, MessageConfUtils.needsMore);
            return false;
        }

        if (args.length == 2) {
            Stat stat = StatsManager.getOrCreate(((Player) sender).getPlayer());

            try {
                switch (args[0]) {
                    case "forging":
                        stat.updateKey("forging_exp", Integer.parseInt(args[1]));
                        MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cSetStat
                                .replace("%stat%", "forging")
                                .replace("%player%", StatsManager.getOfflineOrOnlineDisplay(stat))
                                .replace("%value%", args[1])
                        );
                        return true;
                    case "slaying":
                    default:
                        stat.updateKey("slaying_exp", Integer.parseInt(args[1]));
                        MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cSetStat
                                .replace("%stat%", "slaying")
                                .replace("%player%", StatsManager.getOfflineOrOnlineDisplay(stat))
                                .replace("%value%", args[1])
                        );
                        return true;
                }
            } catch (Exception e){
                e.printStackTrace();
                MessagingHandler.sendSelfMessage(sender, MessageConfUtils.error);
            }
        }

        if (args.length == 3) {
            if (! StatsManager.exists(args[2])) {
                MessagingHandler.sendSelfMessage(sender, MessageConfUtils.noPlayer);
                return false;
            }

            Stat stat = StatsManager.getOrCreate(args[2]);

            try {
                switch (args[0]) {
                    case "forging":
                        stat.updateKey("forging_exp", Integer.parseInt(args[1]));
                        MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cSetStat
                                .replace("%stat%", "forging")
                                .replace("%player%", StatsManager.getOfflineOrOnlineDisplay(stat))
                                .replace("%value%", args[1])
                        );
                        return true;
                    case "slaying":
                    default:
                        stat.updateKey("slaying_exp", Integer.parseInt(args[1]));
                        MessagingHandler.sendSelfMessage(sender, MessageConfUtils.cSetStat
                                .replace("%stat%", "slaying")
                                .replace("%player%", StatsManager.getOfflineOrOnlineDisplay(stat))
                                .replace("%value%", args[1])
                        );
                        return true;
                }
            } catch (Exception e){
                e.printStackTrace();
                MessagingHandler.sendSelfMessage(sender, MessageConfUtils.error);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args == null) return stats();

        if (args.length < 2) return stats();

        if (args.length == 3) {
            List<String> players = new ArrayList<>();

            for (Player player : Dungeons.getInstance().getServer().getOnlinePlayers()) {
                players.add(player.getName());
            }

            return players;
        }

        return new ArrayList<>();
    }

    public List<String> stats() {
        List<String> stats = new ArrayList<>();

        stats.add("slaying");
        stats.add("forging");

        return stats;
    }
}
