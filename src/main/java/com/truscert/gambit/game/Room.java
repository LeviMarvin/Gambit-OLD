package com.truscert.gambit.game;

import org.bukkit.Location;

import java.util.List;

/* This class include the game room and the world that doing game. */
public class Room {
    public int Id;
    public String Timestamp;
    public Location Central;
    public Location Team1;
    public Location Team2;
    public List<String> PlayerList;

    public Room(int Id, String Timestamp, Location Central, Location Team1, Location Team2) {
        this.Id = Id;
        this.Timestamp = Timestamp;
        this.Central = Central;
        this.Team1 = Team1;
        this.Team2 = Team2;
    }

    public int getId() {
        return this.Id;
    }

    public List<String> getPlayers() {
        return this.PlayerList;
    }
}
