package me.happy.hub.command;

import me.happy.hub.Hub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("command.reloadconfig")) {
            sender.sendMessage(ChatColor.RED + "No perms.");
            return true;
        }

        Hub.getInstance().reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Success!");

        return true;
    }
}
