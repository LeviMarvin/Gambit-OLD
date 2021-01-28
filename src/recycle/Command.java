package com.truscert.gambit.command;

import com.truscert.gambit.Gambit;
import com.truscert.gambit.game.Menu;
import com.truscert.gambit.game.data.ConfigData;
import com.truscert.gambit.game.data.Debug;
import com.truscert.gambit.manager.RoomManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    private final static String ERR_CMD = "§l§cCommand Error!";

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players may execute this!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (command.getName().equals("menu")) {
                Menu.getMenu().openMenu(player);
                return true;
            }
            else if (command.getName().equals("leave")) {
                RoomManager.getManager().removePlayer(player);
                player.sendMessage("You have left the room!");
                return true;
            }
            else if (command.getName().equals("create")) {
                //TODO finish the create room. - 1
                RoomManager.getManager().createRoom(
                        ConfigData.getData().roomCenter,
                        ConfigData.getData().roomTeam1,
                        ConfigData.getData().roomTeam2
                );
                player.sendMessage("§aCreated room at " + "" + "(World), " + "" + "(Position)");
                return true;
            }
            else if (command.getName().equals("gambit")) {
                sender.sendMessage("§a+============ Gambit ============+");
                sender.sendMessage("§6Welcome use the Gambit plugin!");
                sender.sendMessage("§6Have fun! :)");
                sender.sendMessage("§a+============ Gambit ============+");
                return true;
            }
            else if (command.getName().equals("debug")) {
                player.sendMessage(player.getLocation() + "");
                Debug.getDebugData().playerLocation = player.getLocation();
                player.sendMessage(Debug.getDebugData().playerLocation + "");
                Gambit.getInstance().getConfig().
                        set("Debug.playerLocation", Debug.getDebugData().playerLocation);
                player.sendMessage(
                        "Ct: " + ConfigData.getData().roomCenter + "\n" +
                        "T1: " + ConfigData.getData().roomTeam1 + "\n" +
                        "T2: " + ConfigData.getData().roomTeam2 + "\n"
                );
                return true;
            }
            sender.sendMessage(ERR_CMD);
            if (Gambit.DEBUGGABLE) {
                sender.sendMessage("DEBUG_INFO: " + command);
                sender.sendMessage("§c§lID:1");
            }
            return true;
        }else if (args.length == 1) {
            String arg0 = args[0];
            String arg1 = args[1];

            if (command.getName().equals("join")) {
                int id = 0;
                try {
                    id = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("§cInvalid room ID.");
                    return false;
                }
                RoomManager.getManager().addPlayer(player, id);
                // Create
                //TODO teleport player to the position.
                RoomManager.getManager().createRoom(
                        ConfigData.getData().roomCenter,
                        ConfigData.getData().roomTeam1,
                        ConfigData.getData().roomTeam2
                );

                player.teleport(ConfigData.getData().roomCenter);
                return true;
            }
            else if (command.getName().equals("remove")) {
                //TODO finish the remove room. - 1
                if(args.length != 1){
                    player.sendMessage("§cInsufficient arguments!");
                    return true;
                }
                int id = 0;
                try{
                    id = Integer.parseInt(args[0]);
                }catch(NumberFormatException e){
                    player.sendMessage("§cInvalid room ID!");
                }
                RoomManager.getManager().removeRoom(id);
                return true;
            }
            sender.sendMessage(ERR_CMD);
            if (Gambit.DEBUGGABLE) {
                sender.sendMessage("DEBUG_INFO: " + command);
                sender.sendMessage("§c§lID:2");
            }
            return true;
        }
        if (Gambit.DEBUGGABLE) {
            sender.sendMessage("§c§lEND");
        }
        return false;
    }
}
