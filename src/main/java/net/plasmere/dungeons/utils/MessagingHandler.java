package net.plasmere.dungeons.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessagingHandler {
    public static void sendSelfMessage(CommandSender sender, String message){
        sender.sendMessage(TextUtils.codedString(message));
    }

    public static void sendSelfMessage(Player sender, String message){
        sender.sendMessage(TextUtils.codedString(message));
    }

    public static void broadcastMessage(){

    }
}
