package com.truscert.gambit.manager;

import com.truscert.gambit.Gambit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager {
    Scoreboard teamScoreboard = Gambit.getInstance().getServer().
            getScoreboardManager().getNewScoreboard();
    Team teamOne = teamScoreboard.registerNewTeam("ONE");
    Team teamTwo = teamScoreboard.registerNewTeam("TWO");
}
