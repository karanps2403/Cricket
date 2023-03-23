package com.cricket.Cricket.service;

import com.cricket.Cricket.controller.MatchesController;
import com.cricket.Cricket.controller.PlayersController;
import com.cricket.Cricket.dto.GameDataDTO;
import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.model.PlayerRecord;
import com.cricket.Cricket.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class StartMatchService {
    private int runsRequired=Integer.MAX_VALUE;
    @Autowired
    private PlayersController playerController;
    @Autowired
    private MatchesController matchesController;
    private final List<Players> players=new ArrayList<>();

    public String start(GameDataDTO gameDataDTO){
        int team1Score = inning(gameDataDTO,0);
        runsRequired=team1Score+1;
        int team2Score = inning(gameDataDTO,1);
        String winner="None";
        if(team1Score>team2Score){
            winner=gameDataDTO.getNameTeam1();
        }
        else if(team1Score<team2Score){
            winner=gameDataDTO.getNameTeam2();
        }

        List<String> playerIds=new ArrayList<>();
        for (Players player : players) {
            playerIds.add(player.getPlayerId());
            playerController.updatePlayer(player);
        }

        Matches matches = Matches.builder()
                .matchId(gameDataDTO.getMatchId())
                .team1Name(gameDataDTO.getNameTeam1())
                .team2Name(gameDataDTO.getNameTeam2())
                .overs(gameDataDTO.getOvers())
                .team1Score(team1Score)
                .team2Score(team2Score)
                .winningTeam(winner)
                .playerIds(playerIds)
                .build();
        matchesController.addMatch(matches);

        System.out.println("------------------------------------");
        if(team1Score>team2Score) return "Team " + winner + " has won by " + (team1Score-team2Score) + " runs";
        else if(team2Score>team1Score) return "Team " + winner + " has won by " + (team2Score-team1Score) + " runs";
        else return "Match is drawn";
    }

    public Players fetchPlayer(String playerName, String teamName){
        Players player= playerController.findByPlayerNameAndTeamName(playerName,teamName);
        if(player==null){
            player=Players.builder()
                    .playerName(playerName)
                    .teamName(teamName)
                    .build();
            playerController.addPlayer(player);
        }
        return player;
    }

    public void updatePlayer(Players player,String matchId, int score,int fours, int sixes, int balls){
        ArrayList<PlayerRecord> playerRecordList=player.getPlayerRecordList();
        if(playerRecordList==null){
            playerRecordList = new ArrayList<>();
        }
        int HighScore=player.getHighScore();
        PlayerRecord record=PlayerRecord.builder()
                .machId(matchId)
                .runsScoredInTheMatch(score)
                .numberOfFours(fours)
                .numberOfSixes(sixes)
                .numberOfBallsFaced(balls)
                .build();

        playerRecordList.add(record);
        if(HighScore < score){
            HighScore=score;
        }

        player.setPlayerRecordList(playerRecordList);
        player.setHighScore(HighScore);

    }

    private int inning(GameDataDTO gameDataDTO,int index) {
        int totalruns=0;
        int wicketsToBeTaken = 0;
        String teamName;
        List<String> team;
        if(index==0){
            team=gameDataDTO.getTeam1();
            teamName=gameDataDTO.getNameTeam1();
        }
        else{
            team=gameDataDTO.getTeam2();
            teamName=gameDataDTO.getNameTeam2();
        }

        Players batsmanZero=fetchPlayer(team.get(0),teamName);
        Players batsmanOne=fetchPlayer(team.get(1),teamName);

        int runsScoreByBatsmanZero=0;
        int runsScoredByBatsmanOne=0;

        int foursByBatsmanZero=0;
        int foursByBatsmanOne=0;

        int sixesByBatsmanZero=0;
        int sixesByBatsmanOne=0;

        int ballsFacedByBatsmanZero=0;
        int ballsFacedByBatsmanOne=0;

        int strike=0;

        int balls = gameDataDTO.getOvers()*6;
        boolean freeHit=false;
        while(wicketsToBeTaken<10){
            int hit = (int)(Math.random()*10);

            if(hit==5){
                if(strike==0){
                    ballsFacedByBatsmanZero++;
                }
                else{
                    ballsFacedByBatsmanOne++;
                }
            }
            else if(hit<7 && hit!=5) {
                if(strike==0){
                    runsScoreByBatsmanZero+=hit;
                    ballsFacedByBatsmanZero++;
                    if(hit==4){
                        foursByBatsmanZero++;
                    }
                    else if(hit==6){
                        sixesByBatsmanZero++;
                    }
                }
                else{
                    runsScoredByBatsmanOne+=hit;
                    ballsFacedByBatsmanOne++;
                    if(hit==4){
                        foursByBatsmanOne++;
                    }
                    else if(hit==6){
                        sixesByBatsmanOne++;
                    }
                }

                if(hit==1 || hit==3){
                    strike^=1;
                }

                totalruns +=hit;
                freeHit = false;
            }
            else if(hit==7 && !freeHit) {
                wicketsToBeTaken++;
                if(strike==0){
                    ballsFacedByBatsmanZero++;
                    updatePlayer(batsmanZero,gameDataDTO.getMatchId(),runsScoreByBatsmanZero,foursByBatsmanZero,sixesByBatsmanZero,ballsFacedByBatsmanZero);
                    players.add(batsmanZero);
                    runsScoreByBatsmanZero=0;
                    foursByBatsmanZero=0;
                    sixesByBatsmanZero=0;
                    ballsFacedByBatsmanZero=0;
                    if(wicketsToBeTaken<10){
                        batsmanZero=fetchPlayer(team.get(wicketsToBeTaken+1),teamName);
                    }
                    else{
                        ballsFacedByBatsmanOne++;
                        updatePlayer(batsmanOne,gameDataDTO.getMatchId(),runsScoredByBatsmanOne,foursByBatsmanOne,sixesByBatsmanOne,ballsFacedByBatsmanOne);
                        players.add(batsmanOne);
                    }
                }
                else{
                    ballsFacedByBatsmanOne++;
                    updatePlayer(batsmanOne,gameDataDTO.getMatchId(),runsScoredByBatsmanOne,foursByBatsmanOne,sixesByBatsmanOne,ballsFacedByBatsmanOne);
                    players.add(batsmanOne);
                    runsScoredByBatsmanOne=0;
                    foursByBatsmanOne=0;
                    sixesByBatsmanOne=0;
                    ballsFacedByBatsmanOne=0;
                    if(wicketsToBeTaken<10){
                        batsmanOne=fetchPlayer(team.get(wicketsToBeTaken+1),teamName);
                    }
                    else{
                        ballsFacedByBatsmanZero++;
                        updatePlayer(batsmanZero,gameDataDTO.getMatchId(),runsScoreByBatsmanZero,foursByBatsmanZero,sixesByBatsmanZero,ballsFacedByBatsmanZero);
                        players.add(batsmanZero);
                    }
                }
                freeHit= false;
            }
            else if(hit==8) { //Wide
                totalruns++;
                freeHit = false;
                System.out.println("Hit: " + hit + " - totalRuns: " + totalruns + " - Balls: " + balls+" wickets: "+wicketsToBeTaken+" Wide Ball");
                continue;
            }
            else if(hit==9){ //No Ball
                totalruns++;
                freeHit= true;
                System.out.println("Hit: " + hit + " - totalRuns: " + totalruns + " - Balls: " + balls+" wickets: "+wicketsToBeTaken+" No Ball");
                continue;
            }
            System.out.println("Hit: " + hit + " - totalRuns: " + totalruns + " - Balls: " + balls+" wickets: "+wicketsToBeTaken);
            balls--;

            if(balls%6==0){
                strike^=1;
            }

            if(totalruns>=runsRequired || balls==0){
                updatePlayer(batsmanZero,gameDataDTO.getMatchId(),runsScoreByBatsmanZero,foursByBatsmanZero,sixesByBatsmanZero,ballsFacedByBatsmanZero);
                players.add(batsmanZero);
                updatePlayer(batsmanOne,gameDataDTO.getMatchId(),runsScoredByBatsmanOne,foursByBatsmanOne,sixesByBatsmanOne,ballsFacedByBatsmanOne);
                players.add(batsmanOne);
                break;
            }
        }
        return totalruns;
    }

}
