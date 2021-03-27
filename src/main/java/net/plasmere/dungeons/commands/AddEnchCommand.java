package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.managers.CustomEnchants;
import net.plasmere.dungeons.utils.TextUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;

public class AddEnchCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            if (args == null) {
                player.sendMessage(TextUtils.codedString(MessageConfUtils.needsMore));
                return false;
            }

            if (args.length < 1) {
                player.sendMessage(TextUtils.codedString(MessageConfUtils.needsMore));
                return false;
            }

            if (player.getItemInHand() == null) return false;

            switch (args[0].toUpperCase(Locale.ROOT)) {
                default:
                case "grabbing_hands":
                    if (args.length == 1) {
                        player.getItemInHand().addUnsafeEnchantment(CustomEnchants.GRABBING_HANDS, 1);
                    } else {
                        player.getItemInHand().addUnsafeEnchantment(CustomEnchants.GRABBING_HANDS, Integer.parseInt(args[1]));
                    }
                    break;
            }

            player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 0.5f, 1f);
            player.sendMessage(TextUtils.codedString("&eSuccessfully summoned!"));
            return true;
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return CustomEnchants.getEnchs();
    }
}
