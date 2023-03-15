package com.cricket.Cricket.dto;

import java.util.List;

public class GameDataDTO {
    private String matchId;
    private String nameTeam1;
    private String nameTeam2;
    private List<String> players = List.of();

    public GameDataDTO() {
        for(int i = 0;i<11;i++){
            players.add("player" + (i+1));
        }
    }

    public String getMatchId() {
        return matchId;
    }

    public String getNameTeam1() {
        return nameTeam1;
    }

    public String getNameTeam2() {
        return nameTeam2;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setNameTeam1(String nameTeam1) {
        this.nameTeam1 = nameTeam1;
    }

    public void setNameTeam2(String nameTeam2) {
        this.nameTeam2 = nameTeam2;
    }
}
