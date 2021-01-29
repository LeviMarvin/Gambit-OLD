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

    public Scoreboard teamScoreboard = Gambit.getInstance().getServer().
            getScoreboardManager().getNewScoreboard();
    public Team teamRed;
    public Team teamBlue;

    public void initManager() {
        teamRed = teamScoreboard.registerNewTeam("Red");
        teamBlue = teamScoreboard.registerNewTeam("Blue");
    }
}
