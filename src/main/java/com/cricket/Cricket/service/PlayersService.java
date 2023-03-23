package com.cricket.Cricket.service;

import com.cricket.Cricket.model.PlayerRecord;
import com.cricket.Cricket.model.Players;
import com.cricket.Cricket.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class PlayersService {
    @Autowired
    private PlayersRepository playersRepository;

    public void addPlayer(Players player){
        playersRepository.save(player);
    }

    public List<Players> findAllPlayers(){
        return playersRepository.findAll();
    }
    public List<Players> findAllByPlayerName(String playerName){
        return playersRepository.findByPlayerName(playerName);
    }
    public List<Players> findAllByTeamName(String teamName){
        return playersRepository.findByTeamName(teamName);
    }
    public Players findByPlayerNameAndTeamName(String playerName,String teamName){
        return playersRepository.findByPlayerNameAndTeamName(playerName,teamName);
    }
    public Object findPlayerById(String playerId){
        if(playersRepository.existsById(playerId)){
            return playersRepository.findById(playerId);
        }
        return "No such player exists with playerId : "+playerId;
    }

    public String deletePlayerById(String playerId){
        playersRepository.deleteById(playerId);
        return "The stats of player with id : "+playerId+" is removed.";
    }

    public String deleteAllPlayers(){
        playersRepository.deleteAll();
        return "The stats of all players are removed.";
    }
    public void updatePlayer(Players player){
        Players oldPlayer=playersRepository.findById(player.getPlayerId()).get();
        oldPlayer.setHighScore(player.getHighScore());
        oldPlayer.setPlayerRecordList(player.getPlayerRecordList());
        playersRepository.save(oldPlayer);
    }

    public void deletePlayerRecord(String playerId, String matchId){
        Players oldPlayer=playersRepository.findById(playerId).get();
        ArrayList<PlayerRecord> record=oldPlayer.getPlayerRecordList();
        record.removeIf(t->(t.getMachId().equals(matchId)));
        int highScore=0;
        for (PlayerRecord playerRecord : record) {
            if (highScore < playerRecord.getRunsScoredInTheMatch()) {
                highScore = playerRecord.getRunsScoredInTheMatch();
            }
        }
        oldPlayer.setHighScore(highScore);
        oldPlayer.setPlayerRecordList(record);
        playersRepository.save(oldPlayer);}
}
