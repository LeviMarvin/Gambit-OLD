package com.truscert.gambit.manager;

import com.truscert.gambit.Gambit;
import com.truscert.gambit.game.Room;
import com.truscert.gambit.utils.Timestamp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To control the gaming world **/

public class RoomManager {
    private static RoomManager manager;
    public static RoomManager getManager() {
        if (manager == null) manager = new RoomManager();
        return manager;
    }

    public RoomManager() {}

    //List of room
    List<Room> Rooms = new ArrayList<Room>();
    //Save where the player teleported.
    public Map<String, Location> locations = new HashMap<String, Location>();
    //Save the player Inventory and Armory.
    Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
    Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();
    //
    int roomSize = 0;

    public Room getRoom(int Id) {
        for (Room room : Rooms) {
            if (room.getId() == Id) {
                return room;
            }
        }
        return null;
    }

    //Add players to the room and save their inventory
    public void addPlayer(Player player, int Id) {
        Room room = getRoom(Id);
        if (room == null) {
            player.sendMessage("§cInvalid Room!");
            return;
        }

        room.getPlayers().add(player.getName());
        inv.put(player.getName(), player.getInventory().getContents());//save inventory
        armor.put(player.getName(), player.getInventory().getArmorContents());

        player.getInventory().setArmorContents(null);
        player.getInventory().clear();

        //TODO: Teleport player to the game world
        locations.put(player.getName(), player.getLocation());
        player.teleport(room.Central);
        //teleport to the room spawn
    }

    //Remove players
    public void removePlayer(Player player){
        //Make a room
        Room room = null;
        for (Room world : Rooms) {
            if (world.getPlayers().contains(player.getName())) {
                //If the room has the player, the arena field would be the arena containing the player.
                room = world;
            }
            //If none is found, the room will be null
        }
        //Make sure it is not null
        if (room == null || !room.getPlayers().contains(player.getName())) {
            player.sendMessage("Invalid operation!");
            return;
        }

        //remove from room
        room.getPlayers().remove(player.getName());

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setContents(inv.get(player.getName()));//restore inventory
        player.getInventory().setArmorContents(armor.get(player.getName()));

        inv.remove(player.getName());//remove entries from hashmaps
        armor.remove(player.getName());
        player.teleport(locations.get(player.getName()));
        locations.remove(player.getName());

        player.setFireTicks(0);
    }
    //Create room
    public Room createRoom(Location central, Location team1, Location team2) {
        int num = roomSize + 1;
        roomSize++;

        Room room = new Room(num, Timestamp.getInstance().getTs(), central, team1, team2);
        Rooms.add(room);

        Gambit.getInstance().getConfig().set("Rooms." + num, serializeLoc(central));
        List<Integer> list = Gambit.getInstance().getConfig().getIntegerList("Rooms.RoomIDs");
        list.add(num);
        Gambit.getInstance().getConfig().set("Rooms.RoomIDs", list);
        Gambit.getInstance().saveConfig();

        return room;
    }

    public Room reloadRoom(Location central, Location team1, Location team2) {
        int num = roomSize + 1;
        roomSize++;

        Room room = new Room(num, Timestamp.getInstance().getTs(), central, team1, team2);
        Rooms.add(room);

        return room;
    }

    public void removeRoom(int id) {
        Room room = getRoom(id);
        if (room == null) {
            return;
        }
        Rooms.remove(room);

        Gambit.getInstance().getConfig().set("Rooms." + id, null);
        List<Integer> list = Gambit.getInstance().getConfig().getIntegerList("Rooms.RoomIDs");
        list.remove(id);
        Gambit.getInstance().getConfig().set("Rooms.RoomIDs", list);
        Gambit.getInstance().saveConfig();
    }

    public boolean isInGame(Player player) {
        for (Room room : Rooms) {
            if (room.getPlayers().contains(player.getName()))
                return true;
        }
        return false;
    }

    public void loadGames() {
        Bukkit.getConsoleSender().sendMessage(Gambit.PREFIX + "§a +初始化游戏房间...");
        roomSize = 0;

        Gambit.getInstance().getConfig();

        if (Gambit.getInstance().getConfig().getIntegerList("Rooms.RoomIDs").isEmpty()) {
            return;
        }

        for (int i : Gambit.getInstance().getConfig().getIntegerList("Rooms.RoomIDs")) {
            Room room = reloadRoom(
                    deserializeLoc(Gambit.getInstance().getConfig().getString("Rooms." + i)),
                    deserializeLoc(Gambit.getInstance().getConfig().getString("Rooms." + i)),
                    deserializeLoc(Gambit.getInstance().getConfig().getString("Rooms." + i))
            );
            room.Id = i;
        }
    }

    public String serializeLoc(Location l) {
        return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
    }

    public Location deserializeLoc(String s) {
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}
