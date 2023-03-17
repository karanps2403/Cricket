package com.cricket.Cricket.service;

import com.cricket.Cricket.dto.GameDataDTO;
import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.model.Players;
import com.cricket.Cricket.repository.MatchesRepository;
import com.cricket.Cricket.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.floor;

@Service
public class StartMatchService {
    private int runsRequired=Integer.MAX_VALUE;
    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private PlayersRepository playersRepository;
    private Matches matches;
    private Players players;

//    String result;
    public String start(GameDataDTO gameDataDTO){
        int team1Score = inning(gameDataDTO.getNameTeam1(),gameDataDTO.getTeam1(),gameDataDTO.getOvers());
        runsRequired=team1Score+1;
        int team2Score = inning(gameDataDTO.getNameTeam2(),gameDataDTO.getTeam2(),gameDataDTO.getOvers());
        String winner="None";
        if(team1Score>team2Score){
            winner=gameDataDTO.getNameTeam1();
        }
        else if(team1Score<team2Score){
            winner=gameDataDTO.getNameTeam2();
        }
        matches=Matches.builder()
                .matchId(gameDataDTO.getMatchId())
                .team1Name(gameDataDTO.getNameTeam1())
                .team2Name(gameDataDTO.getNameTeam2())
                .overs(gameDataDTO.getOvers())
                .team1Score(team1Score)
                .team2Score(team2Score)
                .winningTeam(winner)
                .build();

        System.out.println("------------------------------------");
        if(team1Score>team2Score) return "Team " + winner + " has won by " + (team1Score-team2Score) + " runs";
        else if(team2Score>team1Score) return "Team " + winner + " has won by " + (team2Score-team1Score) + " runs";
        else return "Match is drawn";
    }

    public void initializeMatch(){

    }

    private int inning(String team,List<String> players,int totalOvers) {
        int totalRuns=0;
        int playersToBeBowled = 10;
        int balls = totalOvers*6;
        boolean freeHit=false;
        while(playersToBeBowled>0 && balls>0 && totalRuns<runsRequired){
            int hit = (int)(Math.random()*10);

            if(hit<7 && hit!=5) {
                totalRuns +=hit;
                freeHit = false;
            }
            else if(hit==7 && !freeHit) {
                playersToBeBowled--;
                freeHit= false;
            }
            else if(hit==8) { //Wide
                totalRuns++;
                freeHit = false;
                System.out.println("Hit: " + hit + " - totalRuns: " + totalRuns + " - Balls: " + balls+" wickets: "+playersToBeBowled+" Wide Ball");
                continue;
            }
            else if(hit==9){ //No Ball
                totalRuns++;
                freeHit= true;
                System.out.println("Hit: " + hit + " - totalRuns: " + totalRuns + " - Balls: " + balls+" wickets: "+playersToBeBowled+" No Ball");
                continue;
            }
            System.out.println("Hit: " + hit + " - totalRuns: " + totalRuns + " - Balls: " + balls+" wickets: "+playersToBeBowled);
            balls--;
        }
        return totalRuns;
    }

}
