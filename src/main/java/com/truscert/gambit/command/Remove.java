package com.truscert.gambit.command;

import com.truscert.gambit.manager.RoomManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Remove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //TODO finish the remove room. - 1
        if(args.length != 1){
            sender.sendMessage("§cInsufficient arguments!");
            return true;
        }
        int id = 0;
        try{
            id = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            sender.sendMessage("§cInvalid room ID!");
        }
        RoomManager.getManager().removeRoom(id);
        return true;
    }
}