package com.cricket.Cricket.controller;

import com.cricket.Cricket.model.Players;
import com.cricket.Cricket.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class PlayersController {
    @Autowired
    private PlayersService playersService;

    @PostMapping("/addPlayer")
    public void addPlayer(@RequestBody Players player){
        playersService.addPlayer(player);
    }
    @GetMapping("/findPlayerById/{playerId}")
    public Object findPlayerById(@PathVariable String playerId){
        return playersService.findPlayerById(playerId);
    }
    @GetMapping("/findByPlayerName/{playerName}")
    public List<Players> findAllByPlayerName(@PathVariable String playerName){
        return playersService.findAllByPlayerName(playerName);
    }
    @GetMapping("/findAllByTeamName/{teamName}")
    public List<Players> findAllByTeamName(@PathVariable String teamName){
        return playersService.findAllByTeamName(teamName);
    }
    @GetMapping("/findByPlayerNameAndTeamName/{playerName}/{teamName}")
    public Players findByPlayerNameAndTeamName(@PathVariable String playerName, @PathVariable String teamName){
        return playersService.findByPlayerNameAndTeamName(playerName,teamName);
    }
    @PutMapping("/updatePlayer")
    public void updatePlayer(@RequestBody Players player){
        playersService.updatePlayer(player);
    }
}
