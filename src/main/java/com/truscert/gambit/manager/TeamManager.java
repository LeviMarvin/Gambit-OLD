package com.truscert.gambit.manager;

import com.truscert.gambit.Gambit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager {
    private static TeamManager instance;
    public static TeamManager getTeamManager() {
        if (instance == null) instance = new TeamManager();
        return instance;
    }

    Scoreboard teamScoreboard = Gambit.getInstance().getServer(). getScoreboardManager().getNewScoreboard();
    Team teamOne = teamScoreboard.registerNewTeam("Red");
    Team teamTwo = teamScoreboard.registerNewTeam("Blue");
    teamOne.addEntry();
}
