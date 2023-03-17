package com.cricket.Cricket.service;

import com.cricket.Cricket.model.Players;
import com.cricket.Cricket.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
}
