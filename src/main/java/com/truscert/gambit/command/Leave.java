package com.truscert.gambit.command;

import com.truscert.gambit.manager.RoomManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players may execute this!");
            return true;
        }
        Player player = (Player) sender;
        RoomManager.getManager().removePlayer(player);
        player.sendMessage("§eYou have left the room!");
        return true;
    }
}