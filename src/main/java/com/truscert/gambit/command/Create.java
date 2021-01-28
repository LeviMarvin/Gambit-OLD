package com.truscert.gambit.command;

import com.truscert.gambit.game.Room;
import com.truscert.gambit.game.data.ConfigData;
import com.truscert.gambit.manager.RoomManager;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.truscert.gambit.Gambit.DEBUGGABLE;

public class Create implements CommandExecutor {
    //TODO: FINISH THE CREATE ROOM.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                Room room = RoomManager.getManager().createRoom(
                        ConfigData.getData().roomCenter,
                        ConfigData.getData().roomTeam1,
                        ConfigData.getData().roomTeam2
                );
                sender.sendMessage(
                        "§aCreated room at " +
                                ConfigData.getData().roomCenter.getWorld() + "(World), " + "[" +
                                ConfigData.getData().roomCenter.getBlockX() + "," +
                                ConfigData.getData().roomCenter.getBlockY() + "," +
                                ConfigData.getData().roomCenter.getBlockZ() + "]" + "(Position)"
                );
                if (DEBUGGABLE) {
                    sender.sendMessage("§aThe room id is " + room.getId());
                }
                if (sender instanceof Player) ((Player) sender).teleport(ConfigData.getData().roomTeam1);
                return true;
            case 1:
                if (!(NumberUtils.isNumber(args[0]))) sender.sendMessage("§cThe option parameter must be a number!");

                RoomManager.getManager().createRoom(
                        Integer.parseInt(args[0]),
                        ConfigData.getData().roomCenter,
                        ConfigData.getData().roomTeam1,
                        ConfigData.getData().roomTeam2,
                        sender
                );
                sender.sendMessage(
                        "§aCreated room at " +
                        ConfigData.getData().roomCenter.getWorld() + "(World), " + "[" +
                        ConfigData.getData().roomCenter.getBlockX() + "," +
                        ConfigData.getData().roomCenter.getBlockY() + "," +
                        ConfigData.getData().roomCenter.getBlockZ() + "]" + "(Position)"
                );
                if (DEBUGGABLE) {
                    sender.sendMessage("§aThe room id is " + (Integer.parseInt(args[0])+1) );
                }
                sender.sendMessage("asd");
                return true;
            default:
                return true;
        }
    }
}
