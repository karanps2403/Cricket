package com.cricket.Cricket.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameDataDTO {
    private String matchId;
    private String nameTeam1;
    private String nameTeam2;
    private int overs;
    private List<String> team1 = List.of();
    private List<String> team2 = List.of();

    public String getMatchId() {
        return matchId;
    }

    public String getNameTeam1() {
        return nameTeam1;
    }

    public String getNameTeam2() {
        return nameTeam2;
    }

    public int getOvers() {
        return overs;
    }

    public List<String> getTeam1() {
        return team1;
    }

    public List<String> getTeam2() {
        return team2;
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

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public void setTeam1(List<String> team1) {
        this.team1 = team1;
    }

    public void setTeam2(List<String> team2) {
        this.team2 = team2;
    }
}
