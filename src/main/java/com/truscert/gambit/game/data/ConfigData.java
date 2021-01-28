package com.truscert.gambit.game.data;

import org.bukkit.Location;
import org.bukkit.World;

public class ConfigData {
    private static ConfigData data;
    public static ConfigData getData() {
        if (data == null) data = new ConfigData();
        return data;
    }

    public Location roomCenter;
    public Location roomTeam1;
    public Location roomTeam2;

}
