package net.plasmere.dungeons.commands;


import net.plasmere.dungeons.config.MessageConfUtils;
import net.plasmere.dungeons.utils.PluginUtils;
import net.plasmere.dungeons.utils.TextUtils;
import net.plasmere.dungeons.utils.managers.worlds.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SchemSaveCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (sender instanceof Player) {
//            Player player = ((Player) sender).getPlayer();
//
//            if (args == null) {
//                player.sendMessage(TextUtils.codedString(MessageConfUtils.needsMore));
//                return false;
//            }
//
//            if (args[0].equals("help")){
//                player.sendMessage(TextUtils.codedString("&bUsage: /saveschem <folder (floor)> <name>"));
//                return true;
//            }
//
//            if (args.length < 2) {
//                player.sendMessage(TextUtils.codedString(MessageConfUtils.needsMore));
//                return false;
//            }
//
//            try {
//                LocalSession session = PluginUtils.getWorldEdit().getSession(player);
//                ClipboardHolder selection = session.getClipboard();
//
//                File file = new File(WorldManager.floorsSchemPath + args[0] + "_" + args[1]);
//
//                if (file.exists()) file.delete();
//
//                CuboidClipboard clipboard = (CuboidClipboard) selection.getClipboard();
//                clipboard.saveSchematic(file);
//
//                player.sendMessage(TextUtils.codedString("&eSaved to file &b" + file.getName()));
//
//                return true;
//            } catch (EmptyClipboardException e) {
//                player.sendMessage(TextUtils.codedString(MessageConfUtils.weNoSelection));
//            } catch (Exception e) {
//                player.sendMessage(TextUtils.codedString(MessageConfUtils.error));
//            }
//        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length < 2) {
            return WorldManager.folders;
        }

        return new ArrayList<>();
    }
}
