package com.truscert.gambit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Gambit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§a+============ Gambit ============+");
            sender.sendMessage("§6Welcome use the Gambit plugin!");
            sender.sendMessage("§6Have fun! :)");
            sender.sendMessage("§a+============ Gambit ============+");
            return true;
        } else {
            sender.sendMessage("§cCommand Error!");
            return true;
        }
    }
}
