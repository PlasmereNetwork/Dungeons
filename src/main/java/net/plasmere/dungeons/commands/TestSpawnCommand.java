package net.plasmere.dungeons.commands;

import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.objects.custom.entities.BoneWalkerSkeleton;
import net.plasmere.dungeons.objects.custom.entities.DamageStand;
import net.plasmere.dungeons.objects.custom.entities.VenomousTerrantulaSpider;
import net.plasmere.dungeons.utils.custom.entities.EntityUtils;
import net.plasmere.dungeons.utils.TextUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;

public class TestSpawnCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            if (args == null) {
                player.sendMessage(TextUtils.codedString(MessageConfUtils.needsMore));
                return false;
            }

            if (args.length <= 0) {
                player.sendMessage(TextUtils.codedString(MessageConfUtils.needsMore));
                return false;
            }

            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "bone_walker":
                    new BoneWalkerSkeleton(player.getWorld(), player.getLocation());
                    break;
                case "venomous_tarantula":
                    new VenomousTerrantulaSpider(player.getWorld(), player.getLocation());
                    break;
                case "damage_stand":
                    new DamageStand(player.getWorld(), player.getLocation(), 100d, true);
                    break;
                default:
                    player.sendMessage(TextUtils.codedString("&None selected!"));
                    return false;
            }

            player.playSound(player.getLocation(), Sound.NOTE_PIANO, 0.5f, 1f);
            player.sendMessage(TextUtils.codedString("&eSuccessfully summoned!"));
            return true;
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = EntityUtils.getCustomEntities();
        list.add("damage_stand");

        return list;
    }
}
