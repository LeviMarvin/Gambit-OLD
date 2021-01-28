package com.truscert.gambit.command;

import com.truscert.gambit.Gambit;
import com.truscert.gambit.game.data.ConfigData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players may execute this!");
            return true;
        }
        switch (args.length) {
            case 0:
                Player player = (Player) sender;
                sender.sendMessage(player.getLocation() + "");
                com.truscert.gambit.game.data.Debug.getDebugData().playerLocation = player.getLocation();
                player.sendMessage(com.truscert.gambit.game.data.Debug.getDebugData().playerLocation + "");
                Gambit.getInstance().getConfig().
                        set("Debug.playerLocation", com.truscert.gambit.game.data.Debug.getDebugData().playerLocation);
                player.sendMessage(
                        "Ct: " + ConfigData.getData().roomCenter + "\n" +
                                "T1: " + ConfigData.getData().roomTeam1 + "\n" +
                                "T2: " + ConfigData.getData().roomTeam2 + "\n"
                );
                return true;
            default:
                return true;
        }
    }
}