package com.truscert.gambit.game.data;

import org.bukkit.Location;

public class Debug {
    private static Debug instance;
    public static Debug getDebugData() {
        if (instance == null) instance = new Debug();
        return instance;
    }

    public Location playerLocation;
}
