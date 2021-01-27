package com.truscert.gambit.world;

import com.truscert.gambit.Gambit;
import com.truscert.gambit.utils.Copy;
import org.bukkit.World;

import java.io.IOException;

public class CreateWorld {
    private static CreateWorld instance;
    public static CreateWorld getInstance() {
        if (instance == null) instance = new CreateWorld();
        return instance;
    }

    public World create() throws IOException {
        World world = null;
        Copy.copy(Gambit.getInstance().getDataFolder().toString(), null);

        return world;
    }
}
