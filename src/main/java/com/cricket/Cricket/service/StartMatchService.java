package com.cricket.Cricket.service;

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
    private PlayersService playersService;
    @Autowired
    private MatchesService matchesService;
    private Matches matches;
    private List<Players> players=new ArrayList<>();

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
        for(int i=0;i<players.size();i++){
            playerIds.add(players.get(i).getPlayerId());
            playersService.updatePlayer(players.get(i));
        }

        matches=Matches.builder()
                .matchId(gameDataDTO.getMatchId())
                .team1Name(gameDataDTO.getNameTeam1())
                .team2Name(gameDataDTO.getNameTeam2())
                .overs(gameDataDTO.getOvers())
                .team1Score(team1Score)
                .team2Score(team2Score)
                .winningTeam(winner)
                .playerIds(playerIds)
                .build();
        matchesService.addMatch(matches);

        System.out.println("------------------------------------");
        if(team1Score>team2Score) return "Team " + winner + " has won by " + (team1Score-team2Score) + " runs";
        else if(team2Score>team1Score) return "Team " + winner + " has won by " + (team2Score-team1Score) + " runs";
        else return "Match is drawn";
    }

    public Players fetchPlayer(String playerName, String teamName){
        Players player=playersService.findByPlayerNameAndTeamName(playerName,teamName);
        if(player==null){
            player=Players.builder()
                    .playerName(playerName)
                    .teamName(teamName)
                    .build();
            playersService.addPlayer(player);
        }
        return player;
    }

    public void updatePlayer(Players player,String matchId, int score){
//        ArrayList<PlayerRecord> playerRecordList=player.getPlayerRecordList();
//        if(playerRecordList==null){
//            playerRecordList = new ArrayList<>();
//        }
        int HighScore=player.getHighScore();
        PlayerRecord record=PlayerRecord.builder()
                .machId(matchId)
                .runsScoredInTheMatch(score)
                .build();

//        playerRecordList.add(record);
        if(HighScore < score){
            HighScore=score;
        }

//        player.setPlayerRecordList(playerRecordList);
        player.setHighScore(HighScore);

    }

    private int inning(GameDataDTO gameDataDTO,int index) {
        int totalRuns=0;
        int playersToBeBowled = 0;
        String teamName;
        List<String> team;
        //List<Players> players=new ArrayList<>();
        if(index==0){
            team=gameDataDTO.getTeam1();
            teamName=gameDataDTO.getNameTeam1();
        }
        else{
            team=gameDataDTO.getTeam2();
            teamName=gameDataDTO.getNameTeam2();
        }

        Players batsman0=fetchPlayer(team.get(0),teamName);
        Players batsman1=fetchPlayer(team.get(1),teamName);

        int score0=0;
        int score1=0;

        int strike=0;

        int balls = gameDataDTO.getOvers()*6;
        boolean freeHit=false;
        while(playersToBeBowled<10){
            int hit = (int)(Math.random()*10);

            if(hit<7 && hit!=5) {
                if(strike==0){
                    score0+=hit;
                }
                else{
                    score1+=hit;
                }

                if(hit==1 || hit==3){
                    strike^=1;
                }

                totalRuns +=hit;
                freeHit = false;
            }
            else if(hit==7 && !freeHit) {
                playersToBeBowled++;
                if(strike==0){
                    updatePlayer(batsman0,gameDataDTO.getMatchId(),score0);
                    players.add(batsman0);
                    score0=0;
                    if(playersToBeBowled<10){
                        batsman0=fetchPlayer(team.get(playersToBeBowled+1),teamName);
                    }
                    else{
                        updatePlayer(batsman1,gameDataDTO.getMatchId(),score1);
                        players.add(batsman1);
                    }
                }
                else{
                    updatePlayer(batsman1,gameDataDTO.getMatchId(),score1);
                    players.add(batsman1);
                    score1=0;
                    if(playersToBeBowled<10){
                        batsman1=fetchPlayer(team.get(playersToBeBowled+1),teamName);
                    }
                    else{
                        updatePlayer(batsman0,gameDataDTO.getMatchId(),score0);
                        players.add(batsman0);
                    }
                }
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

            if(balls%6==0){
                strike^=1;
            }

            if(totalRuns>=runsRequired || balls==0){
                updatePlayer(batsman0,gameDataDTO.getMatchId(),score0);
                players.add(batsman0);
                updatePlayer(batsman1,gameDataDTO.getMatchId(),score1);
                players.add(batsman1);
                break;
            }
        }
        return totalRuns;
    }

}
