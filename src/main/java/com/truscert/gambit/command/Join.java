package com.truscert.gambit.command;

import com.truscert.gambit.manager.RoomManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players may execute this!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 1) {
            int id;
            try {
                id = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cInvalid room ID.");
                return false;
            }
            RoomManager.getManager().addPlayer(player, id);
            player.teleport(RoomManager.getManager().getRoom(id).Central);
            sender.sendMessage("§eYou have joined the game!");
        } else {
            sender.sendMessage("§cCommand Error!");
        }
        return true;
    }
}
