package net.plasmere.dungeons.utils;

import org.bukkit.command.CommandSender;

public class MessagingHandler {
    public static void sendSelfMessage(CommandSender sender, String message){
        sender.sendMessage(TextUtils.codedString(message));
    }
}
